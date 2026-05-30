package controller;

import model.Cliente;
import model.repository.ClienteRepository;
import service.ClienteService;
import java.util.List;

public class ClienteController {
    private ClienteService service;

    public ClienteController(ClienteRepository repository) {
        this.service = new ClienteService(repository);
    }

    public void adicionarCliente(String nome, String cpf, String telefone, String email, String login, String senha) {
        try {
            Cliente novo = new Cliente(null, nome, cpf, telefone, email, login, senha);
            service.cadastrarCliente(novo);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }

    public Cliente realizarLogin(String login, String senha) {
        return service.realizarLogin(login, senha);
    }

    public void exibirClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        List<Cliente> lista = service.listarClientes();
        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : lista) {
            System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome() + " | CPF: " + c.getCpf() + " | Tel: " + c.getTelefone() + " | Login: " + c.getLogin());
        }
    }
}
