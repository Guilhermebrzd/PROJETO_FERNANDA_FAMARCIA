package model.repository;

import model.Medicamento;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicamentoRepository {
    private List<Medicamento> medicamentos = new ArrayList<>();
    private int contadorId = 1;

    public void salvar(Medicamento medicamento) {
        if (medicamento.getId() == null) {
            medicamento.setId(contadorId++);
            medicamentos.add(medicamento);
        } else {
            atualizar(medicamento);
        }
    }

    private void atualizar(Medicamento medAtualizado) {
        for (int i = 0; i < medicamentos.size(); i++) {
            if (medicamentos.get(i).getId().equals(medAtualizado.getId())) {
                medicamentos.set(i, medAtualizado);
                return;
            }
        }
    }

    public List<Medicamento> listarTodos() {
        return new ArrayList<>(medicamentos);
    }

    public Optional<Medicamento> buscarPorId(Integer id) {
        return medicamentos.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public Optional<Medicamento> buscarPorNome(String nome) {
        return medicamentos.stream().filter(m -> m.getNome().equalsIgnoreCase(nome)).findFirst();
    }

    public void deletar(Integer id) {
        medicamentos.removeIf(m -> m.getId().equals(id));
    }
}
