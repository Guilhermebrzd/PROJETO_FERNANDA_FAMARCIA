package controller;

import model.Medicamento;
import model.repository.MedicamentoRepository;
import service.MedicamentoService;
import java.util.List;

public class MedicamentoController {
    private MedicamentoService service;

    // CONSTRUTOR QUE RESOLVE O ERRO DA LINHA 23 DA MAIN
    public MedicamentoController(MedicamentoRepository repository) {
        this.service = new MedicamentoService(repository);
    }

    public void adicionarMedicamento(String nome, String laboratorio, double preco, int qtd, String validade) {
        try {
            Medicamento novo = new Medicamento(null, nome, laboratorio, preco, qtd, validade);
            service.cadastrarMedicamento(novo);
            System.out.println("Medicamento cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public void exibirEstoque() {
        System.out.println("\n--- ESTOQUE DE MEDICAMENTOS ---");
        List<Medicamento> lista = service.listarMedicamentos();
        if (lista.isEmpty()) {
            System.out.println("Estoque vazio.");
            return;
        }
        for (Medicamento m : lista) {
            System.out.println("ID: " + m.getId() + " | Nome: " + m.getNome() + " | Qtd: " + m.getQuantidadeEstoque() + " | Preço: R$ " + m.getPreco());
        }
    }
}
