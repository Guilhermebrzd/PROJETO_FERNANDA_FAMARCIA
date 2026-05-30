package service;

import model.Funcionario;
import model.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;

public class FuncionarioService {
    private FuncionarioRepository repository;

    public FuncionarioService() {
        this.repository = new FuncionarioRepository();
    }

    public void cadastrarFuncionario(Funcionario funcionario) throws IllegalArgumentException {
        if (funcionario.getCpf() == null || funcionario.getCpf().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        if (funcionario.getLogin() == null || funcionario.getSenha() == null) {
            throw new IllegalArgumentException("Login e Senha são obrigatórios para acesso ao sistema.");
        }

        Optional<Funcionario> existente = repository.buscarPorLogin(funcionario.getLogin());
        if (existente.isPresent() && !existente.get().getId().equals(funcionario.getId())) {
            throw new IllegalArgumentException("Login já está em uso por outro funcionário.");
        }

        repository.salvar(funcionario);
    }

    public Funcionario autenticar(String login, String senha) {
        Optional<Funcionario> funcionario = repository.buscarPorLogin(login);
        if (funcionario.isPresent() && funcionario.get().getSenha().equals(senha)) {
            return funcionario.get(); // Autenticação bem-sucedida, retorna o funcionário logado
        }
        return null; // Falha na autenticação
    }

    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    public void removerFuncionario(Integer id) {
        repository.deletar(id);
    }
}