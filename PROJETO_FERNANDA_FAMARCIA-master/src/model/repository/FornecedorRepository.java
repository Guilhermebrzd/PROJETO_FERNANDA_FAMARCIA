package model.repository;

import model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornecedorRepository {
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private int contadorId = 1;

    public void salvar(Fornecedor fornecedor) {
        if (fornecedor.getId() == null) {
            fornecedor.setId(contadorId++);
            fornecedores.add(fornecedor);
        } else {
            atualizar(fornecedor);
        }
    }

    private void atualizar(Fornecedor fornecedorAtualizado) {
        for (int i = 0; i < fornecedores.size(); i++) {
            if (fornecedores.get(i).getId().equals(fornecedorAtualizado.getId())) {
                fornecedores.set(i, fornecedorAtualizado);
                return;
            }
        }
    }

    public List<Fornecedor> listarTodos() {
        return new ArrayList<>(fornecedores);
    }

    public Optional<Fornecedor> buscarPorId(Integer id) {
        return fornecedores.stream().filter(f -> f.getId().equals(id)).findFirst();
    }

    public void deletar(Integer id) {
        fornecedores.removeIf(f -> f.getId().equals(id));
    }
}