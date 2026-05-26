package model.repository;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioRepository {
    private List<Funcionario> funcionarios = new ArrayList<>();
    private int contadorId = 1;

    public void salvar(Funcionario funcionario) {
        if (funcionario.getId() == null) {
            funcionario.setId(contadorId++);
            funcionarios.add(funcionario);
        } else {
            atualizar(funcionario);
        }
    }

    private void atualizar(Funcionario funcionarioAtualizado) {
        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getId().equals(funcionarioAtualizado.getId())) {
                funcionarios.set(i, funcionarioAtualizado);
                return;
            }
        }
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios);
    }

    public Optional<Funcionario> buscarPorId(Integer id) {
        return funcionarios.stream().filter(f -> f.getId().equals(id)).findFirst();
    }

    public Optional<Funcionario> buscarPorLogin(String login) {
        return funcionarios.stream().filter(f -> f.getLogin().equals(login)).findFirst();
    }

    public void deletar(Integer id) {
        funcionarios.removeIf(f -> f.getId().equals(id));
    }
}