package service;

import model.Cliente;
import model.repository.ClienteRepository;
import java.util.List;
import java.util.Optional;

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
        if (cliente.getLogin() == null || cliente.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O login do cliente é obrigatório.");
        }
        
        // Validação de duplicidade de Login
        if (repository.buscarPorLogin(cliente.getLogin()).isPresent()) {
            throw new IllegalArgumentException("Este login já existe. Por favor, tente outro.");
        }

        // Se tiver CPF, valida duplicidade de CPF também
        if (cliente.getCpf() != null && !cliente.getCpf().trim().isEmpty()) {
            if (repository.buscarPorCpf(cliente.getCpf()).isPresent()) {
                throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF.");
            }
        }

        repository.salvar(cliente);
    }

    public Cliente realizarLogin(String login, String senha) {
        Optional<Cliente> clienteOpt = repository.buscarPorLogin(login);
        if (clienteOpt.isPresent() && clienteOpt.get().getSenha().equals(senha)) {
            return clienteOpt.get();
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        return repository.listarTodos();
    }

    public void removerCliente(Integer id) {
        repository.deletar(id);
    }
}
