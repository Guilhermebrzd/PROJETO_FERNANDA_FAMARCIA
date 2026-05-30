package controller;

import model.Venda;
import model.repository.MedicamentoRepository;
import model.repository.VendaRepository;
import service.VendaService;
import java.util.List;

public class VendaController {
    private VendaService service;

    // O controlador recebe os mesmos repositórios compartilhados do Main
    public VendaController(VendaRepository vendaRepository, MedicamentoRepository medicamentoRepository) {
        this.service = new VendaService(vendaRepository, medicamentoRepository);
    }

    public void realizarVenda(String data, String cliente, String nomeMedicamento, int quantidadeDesejada) {
        try {
            Venda vendaRealizada = service.realizarVenda(data, cliente, nomeMedicamento, quantidadeDesejada);
            System.out.println("\nVenda realizada com sucesso!");
            emitirComprovante(vendaRealizada);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private void emitirComprovante(Venda venda) {
        System.out.println("\n========================================");
        System.out.println("          CUPOM FISCAL - FARMÁCIA        ");
        System.out.println("========================================");
        System.out.println("ID da Venda: " + venda.getId());
        System.out.println("Data: " + venda.getData());
        System.out.println("Cliente: " + venda.getCliente());
        System.out.println("----------------------------------------");
        System.out.println("Itens vendidos:");
        for (String item : venda.getItens()) {
            System.out.println("- " + item);
        }
        System.out.println("----------------------------------------");
        System.out.println("TOTAL A PAGAR: R$ " + venda.getValorTotal());
        System.out.println("========================================");
    }

    public void listarTodasVendas() {
        System.out.println("\n--- LISTAGEM DE HISTÓRICO DE VENDAS ---");
        List<Venda> lista = service.listarTodasVendas();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma venda realizada até o momento.");
            return;
        }
        for (Venda v : lista) {
            System.out.println(v);
        }
    }

    public void cancelarVenda(int idVenda) {
        try {
            service.cancelarVenda(idVenda);
            System.out.println("Venda " + idVenda + " cancelada e excluída com sucesso.");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
