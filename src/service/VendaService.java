package br.com.farmacia.controller;

import br.com.farmacia.model.Venda;
import java.util.ArrayList;

public class VendaService {
    private ArrayList<Venda> listaVendas = new ArrayList<>();
    private ArrayList<String> estoqueNomes = new ArrayList<>();
    private ArrayList<Integer> estoqueQuantidades = new ArrayList<>();
    private ArrayList<Double> estoquePrecos = new ArrayList<>();

    public VendaService() {
        estoqueNomes.add("Paracetamol");
        estoqueQuantidades.add(50);
        estoquePrecos.add(12.50);

        estoqueNomes.add("Amoxicilina");
        estoqueQuantidades.add(20);
        estoquePrecos.add(45.00);

        estoqueNomes.add("Ibuprofeno");
        estoqueQuantidades.add(35);
        estoquePrecos.add(18.20);
    }

    public void exibirEstoque() {
        System.out.println("\n--- RELATÓRIO DE ESTOQUE ATUAL ---");
        for (int i = 0; i < estoqueNomes.size(); i++) {
            System.out.println("Medicamento: " + estoqueNomes.get(i) +
                    " | Qtd: " + estoqueQuantidades.get(i) +
                    " | Preço Unitário: R$ " + estoquePrecos.get(i));
        }
    }

    public void atualizarEstoque(String nome, int quantidade) {
        boolean encontrado = false;
        for (int i = 0; i < estoqueNomes.size(); i++) {
            if (estoqueNomes.get(i).equalsIgnoreCase(nome)) {
                estoqueQuantidades.set(i, quantidade);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Medicamento não localizado no estoque.");
        }
    }

    public void realizarVenda(int idVenda, String data, String cliente, String nomeMedicamento, int quantidadeDesejada) {
        int indiceMedicamento = -1;
        for (int i = 0; i < estoqueNomes.size(); i++) {
            if (estoqueNomes.get(i).equalsIgnoreCase(nomeMedicamento)) {
                indiceMedicamento = i;
                break;
            }
        }

        if (indiceMedicamento == -1) {
            System.out.println("Erro: Medicamento não encontrado no estoque.");
            return;
        }

        int qtdAtual = estoqueQuantidades.get(indiceMedicamento);
        if (qtdAtual < quantidadeDesejada) {
            System.out.println("Erro: Estoque insuficiente! Temos apenas " + qtdAtual + " unidades.");
            return;
        }

        estoqueQuantidades.set(indiceMedicamento, qtdAtual - quantidadeDesejada);
        double valorTotal = estoquePrecos.get(indiceMedicamento) * quantidadeDesejada;

        ArrayList<String> itensVenda = new ArrayList<>();
        itensVenda.add(nomeMedicamento + " (x" + quantidadeDesejada + ")");

        Venda novaVenda = new Venda(idVenda, data, cliente, itensVenda, valorTotal);
        listaVendas.add(novaVenda);

        System.out.println("\nVenda realizada com sucesso!");
        emitirComprovante(novaVenda);
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
        if (listaVendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada até o momento.");
            return;
        }
        for (Venda v : listaVendas) {
            System.out.println(v);
        }
    }

    public void cancelarVenda(int idVenda) {
        for (int i = 0; i < listaVendas.size(); i++) {
            if (listaVendas.get(i).getId() == idVenda) {
                listaVendas.remove(i);
                System.out.println("Venda " + idVenda + " cancelada e excluída com sucesso.");
                return;
            }
        }
        System.out.println("Venda com ID " + idVenda + " não encontrada.");
    }

    public void exibirRelatorioLucro() {
        double lucroTotal = 0;
        for (Venda v : listaVendas) {
            lucroTotal += v.getValorTotal();
        }
        System.out.println("\n--- RELATÓRIO DE FATURAMENTO / LUCRO MENSAL ---");
        System.out.println("Total faturado no mês corrente: R$ " + lucroTotal);
    }
}