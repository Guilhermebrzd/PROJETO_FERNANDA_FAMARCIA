package model.repository;

import model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepository {
    private List<Cliente> clientes = new ArrayList<>();
    private int contadorId = 1;

    public void salvar(Cliente cliente) {
        if (cliente.getId() == null) {
            cliente.setId(contadorId++);
            clientes.add(cliente);
        } else {
            atualizar(cliente);
        }
    }

    private void atualizar(Cliente clienteAtualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(clienteAtualizado.getId())) {
                clientes.set(i, clienteAtualizado);
                return;
            }
        }
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(clientes);
    }

    public Optional<Cliente> buscarPorId(Integer id) {
        return clientes.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst();
    }

    public void deletar(Integer id) {
        clientes.removeIf(c -> c.getId().equals(id));
    }
}
