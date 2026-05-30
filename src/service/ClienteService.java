package service;

import model.Cliente;
import model.repository.ClienteRepository;
import java.util.List;

public class ClienteService {
    private ClienteRepository repository;

    public ClienteService() {
        this.repository = new ClienteRepository();
    }

    // Construtor alternativo para compartilhar o repositório se necessário
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public void cadastrarCliente(Cliente cliente) throws IllegalArgumentException {
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do cliente é obrigatório.");
        }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }

        // Validação de duplicidade
        if (repository.buscarPorCpf(cliente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe un cliente cadastrado com este CPF.");
        }

        repository.salvar(cliente);
    }

    public List<Cliente> listarClientes() {
        return repository.listarTodos();
    }

    public void removerCliente(Integer id) {
        repository.deletar(id);
    }
}
