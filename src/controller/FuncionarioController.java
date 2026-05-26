package controller;

import model.Funcionario;
import service.FuncionarioService;
import java.util.List;

public class FuncionarioController {
    private FuncionarioService service;
    private Funcionario funcionarioLogado;

    public FuncionarioController() {
        this.service = new FuncionarioService();
    }

    public void adicionarFuncionario(String nome, String cpf, String cargo, String login, String senha, String nivelAcesso) {
        try {
            Funcionario novo = new Funcionario(null, nome, cpf, cargo, login, senha, nivelAcesso);
            service.cadastrarFuncionario(novo);
            System.out.println("Funcionário cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    public boolean realizarLogin(String login, String senha) {
        funcionarioLogado = service.autenticar(login, senha);
        if (funcionarioLogado != null) {
            System.out.println("Login realizado com sucesso. Bem-vindo, " + funcionarioLogado.getNome() + " [" + funcionarioLogado.getNivelAcesso() + "]");
            return true;
        } else {
            System.err.println("Login ou senha incorretos.");
            return false;
        }
    }

    public void exibirFuncionarios() {
        List<Funcionario> lista = service.listarFuncionarios();
        System.out.println("--- Lista de Funcionários ---");
        for (Funcionario f : lista) {
            System.out.println("ID: " + f.getId() + " | Nome: " + f.getNome() + " | Cargo: " + f.getCargo() + " | Nível: " + f.getNivelAcesso());
        }
    }
}