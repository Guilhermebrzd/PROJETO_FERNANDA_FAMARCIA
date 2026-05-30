package service;

import model.Medicamento;
import model.repository.MedicamentoRepository;
import java.util.List;
import java.util.Optional;

public class MedicamentoService {
    private MedicamentoRepository repository;

    public MedicamentoService() {
        this.repository = new MedicamentoRepository();
    }

    public MedicamentoService(MedicamentoRepository repository) {
        this.repository = repository;
    }

    public void cadastrarMedicamento(Medicamento medicamento) throws IllegalArgumentException {
        if (medicamento.getNome() == null || medicamento.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do medicamento é obrigatório.");
        }
        if (medicamento.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço do medicamento deve ser maior que zero.");
        }
        if (medicamento.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }

        repository.salvar(medicamento);
    }

    public List<Medicamento> listarMedicamentos() {
        return repository.listarTodos();
    }

    public Optional<Medicamento> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome);
    }

    public void atualizarEstoque(Integer id, int novaQuantidade) {
        Optional<Medicamento> medOpt = repository.buscarPorId(id);
        if (medOpt.isPresent()) {
            Medicamento m = medOpt.get();
            m.setQuantidadeEstoque(novaQuantidade);
            repository.salvar(m);
        } else {
            throw new IllegalArgumentException("Medicamento não localizado para atualização.");
        }
    }

    public void removerMedicamento(Integer id) {
        repository.deletar(id);
    }
}
