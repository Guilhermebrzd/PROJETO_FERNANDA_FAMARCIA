package model;

public class Medicamento {
    private int id;
    private String nome;
    private String laboratorio;
    private double preco;
    private int quantidadeEstoque;
    private String validade;

    public Medicamento(int id, String nome, String laboratorio, double preco, int quantidadeEstoque, String validade) {

        this.id = id;
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.validade = validade;
    }
}
