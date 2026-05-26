package controller;

import model.Fornecedor;
import service.FornecedorService;
import java.util.List;

public class FornecedorController {
    private FornecedorService service;

    public FornecedorController() {
        this.service = new FornecedorService();
    }

    public void adicionarFornecedor(String razaoSocial, String cnpj, String telefone, String email) {
        try {
            Fornecedor novo = new Fornecedor(null, razaoSocial, cnpj, telefone, email);
            service.cadastrarFornecedor(novo);
            System.out.println("Fornecedor cadastrado com sucesso.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao cadastrar fornecedor: " + e.getMessage());
        }
    }

    public void exibirFornecedores() {
        List<Fornecedor> lista = service.listarFornecedores();
        System.out.println("--- Lista de Fornecedores ---");
        for (Fornecedor f : lista) {
            System.out.println("ID: " + f.getId() + " | Empresa: " + f.getRazaoSocial() + " | CNPJ: " + f.getCnpj());
        }
    }
}