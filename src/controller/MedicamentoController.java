package controller;

import model.Medicamento;
import service.MedicamentoService;
import java.util.List;

public class MedicamentoController {
    private MedicamentoService service;

    public MedicamentoController() {
        this.service = new MedicamentoService();
    }

    public void adicionarMedicamento(String nome, String fabricante, double preco, int quantidadeEstoque) {
        try {
            Medicamento novo = new Medicamento(null, nome, fabricante, preco, quantidadeEstoque);
            service.cadastrarMedicamento(novo);
            System.out.println("Medicamento cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar medicamento: " + e.getMessage());
        }
    }

    public void exibirMedicamentos() {
        List<Medicamento> lista = service.listarMedicamentos();
        System.out.println("--- Lista de Medicamentos ---");
        if (lista.isEmpty()) {
            System.out.println("Nenhum medicamento cadastrado no sistema.");
            return;
        }
        for (Medicamento m : lista) {
            System.out.println("ID: " + m.getId() + " | Nome: " + m.getNome() + " | Preço: R$ " + m.getPreco() + " | Estoque: " + m.getQuantidadeEstoque());
        }
    }
}
