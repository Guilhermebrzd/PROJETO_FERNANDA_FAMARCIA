package model;

public class Medicamento {
    private Integer id;
    private String nome;
    private String laboratorio;
    private double preco;
    private int quantidadeEstoque;
    private String validade;

    public Medicamento(Integer id, String nome, String laboratorio, double preco, int quantidadeEstoque, String validade) {
        this.id = id;
        this.nome = nome;
        this.laboratorio = laboratorio;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.validade = validade;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
    public String getValidade() { return validade; }
    public void setValidade(String validade) { this.validade = validade; }
}
