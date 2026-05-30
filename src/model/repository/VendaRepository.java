package model.repository;

import model.Venda;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VendaRepository {
    private List<Venda> vendas = new ArrayList<>();
    private int contadorId = 1;

    public void salvar(Venda venda) {
        if (venda.getId() == null) {
            venda.setId(contadorId++);
            vendas.add(venda);
        } else {
            atualizar(venda);
        }
    }

    private void atualizar(Venda vendaAtualizada) {
        for (int i = 0; i < vendas.size(); i++) {
            if (vendas.get(i).getId().equals(vendaAtualizada.getId())) {
                vendas.set(i, vendaAtualizada);
                return;
            }
        }
    }

    public List<Venda> listarTodos() {
        return new ArrayList<>(vendas);
    }

    public Optional<Venda> buscarPorId(Integer id) {
        return vendas.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public void deletar(Integer id) {
        vendas.removeIf(v -> v.getId().equals(id));
    }
}
