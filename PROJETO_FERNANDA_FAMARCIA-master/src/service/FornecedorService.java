package service;

import model.Fornecedor;
import model.repository.FornecedorRepository;
import java.util.List;

public class FornecedorService {
    private FornecedorRepository repository;

    public FornecedorService() {
        this.repository = new FornecedorRepository();
    }

    public void cadastrarFornecedor(Fornecedor fornecedor) throws IllegalArgumentException {
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().isEmpty()) {
            throw new IllegalArgumentException("CNPJ é obrigatório.");
        }
        if (fornecedor.getRazaoSocial() == null || fornecedor.getRazaoSocial().isEmpty()) {
            throw new IllegalArgumentException("A Razão Social é obrigatória.");
        }
        repository.salvar(fornecedor);
    }

    public List<Fornecedor> listarFornecedores() {
        return repository.listarTodos();
    }

    public void removerFornecedor(Integer id) {
        repository.deletar(id);
    }
}