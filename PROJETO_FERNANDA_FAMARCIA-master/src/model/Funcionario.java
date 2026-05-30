package model;

public class Funcionario {
    private Integer id;
    private String nome;
    private String cpf;
    private String cargo;
    private String login;
    private String senha;
    private String nivelAcesso; // Ex: "ADMINISTRADOR", "FARMACEUTICO", "BALCONISTA"

    public Funcionario(Integer id, String nome, String cpf, String cargo, String login, String senha, String nivelAcesso) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNivelAcesso() { return nivelAcesso; }
    public void setNivelAcesso(String nivelAcesso) { this.nivelAcesso = nivelAcesso; }
}