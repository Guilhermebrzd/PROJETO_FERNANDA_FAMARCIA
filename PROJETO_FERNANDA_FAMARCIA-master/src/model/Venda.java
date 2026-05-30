package br.com.farmacia.model;

import java.util.ArrayList;

public class Venda {
    private int id;
    private String data;
    private String cliente;
    private ArrayList<String> itens;
    private double valorTotal;

    public Venda() {
        this.itens = new ArrayList<>();
    }

    public Venda(int id, String data, String cliente, ArrayList<String> itens, double valorTotal) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public ArrayList<String> getItens() {
        return itens;
    }

    public void setItens(ArrayList<String> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Venda ID: " + id + " | Cliente: " + cliente + " | Data: " + data + " | Total: R$ " + valorTotal;
    }
}