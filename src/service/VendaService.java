package service;

import model.Venda;
import model.Medicamento;
import model.repository.VendaRepository;
import model.repository.MedicamentoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendaService {
    private VendaRepository vendaRepository;
    private MedicamentoRepository medicamentoRepository;

    // Recebe os repositórios criados no sistema para garantir consistência de dados
    public VendaService(VendaRepository vendaRepository, MedicamentoRepository medicamentoRepository) {
        this.vendaRepository = vendaRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public Venda realizarVenda(String data, String cliente, String nomeMedicamento, int quantidadeDesejada) throws IllegalArgumentException {
        // 1. Busca o medicamento real no repositório de medicamentos
        Optional<Medicamento> medOpt = medicamentoRepository.buscarPorNome(nomeMedicamento);
        
        if (!medOpt.isPresent()) {
            throw new IllegalArgumentException("Erro: Medicamento '" + nomeMedicamento + "' não encontrado no estoque.");
        }

        Medicamento medicamento = medOpt.get();

        // 2. Verifica se tem estoque suficiente
        if (medicamento.getQuantidadeEstoque() < quantidadeDesejada) {
            throw new IllegalArgumentException("Erro: Estoque insuficiente! Temos apenas " + medicamento.getQuantidadeEstoque() + " unidades de " + medicamento.getNome() + ".");
        }

        // 3. Atualiza o estoque do medicamento dando baixa
        medicamento.setQuantidadeEstoque(medicamento.getQuantidadeEstoque() - quantidadeDesejada);
        medicamentoRepository.salvar(medicamento);

        // 4. Calcula o valor total dinamicamente com base no preço do modelo
        double valorTotal = medicamento.getPreco() * quantidadeDesejada;

        ArrayList<String> itensVenda = new ArrayList<>();
        itensVenda.add(medicamento.getNome() + " (x" + quantidadeDesejada + ") - R$ " + medicamento.getPreco() + " cada");

        // 5. Instancia a venda (ID será gerado pelo contador do Repository)
        Venda novaVenda = new Venda(null, data, cliente, itensVenda, valorTotal);
        vendaRepository.salvar(novaVenda);

        return novaVenda;
    }

    public List<Venda> listarTodasVendas() {
        return vendaRepository.listarTodos();
    }

    public void cancelarVenda(int idVenda) throws IllegalArgumentException {
        Optional<Venda> vendaOpt = vendaRepository.buscarPorId(idVenda);
        if (vendaOpt.isPresent()) {
            vendaRepository.deletar(idVenda);
        } else {
            throw new IllegalArgumentException("Venda com ID " + idVenda + " não encontrada.");
        }
    }
}
