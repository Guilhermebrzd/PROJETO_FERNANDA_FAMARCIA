import controller.*;
import model.Medicamento;
import model.Cliente;
import model.repository.ClienteRepository;
import model.repository.MedicamentoRepository;
import model.repository.VendaRepository;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    private static MedicamentoRepository medicamentoRepository = new MedicamentoRepository();
    private static ClienteRepository clienteRepository = new ClienteRepository();
    private static VendaRepository vendaRepository = new VendaRepository();

    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static FornecedorController fornecedorController = new FornecedorController();
    private static ClienteController clienteController = new ClienteController(clienteRepository);
    private static MedicamentoController medicamentoController = new MedicamentoController(medicamentoRepository);
    private static VendaController vendaController = new VendaController(vendaRepository, medicamentoRepository);

    public static void main(String[] args) {
        carregarDadosIniciais();

        System.out.println("========================================");
        System.out.println("    BEM-VINDO AO JL SMART PHARMA        ");
        System.out.println("========================================");

        boolean sair = false;
        while (!sair) {
            System.out.println("\n--- TELA INICIAL ---");
            System.out.println("1 - Login Funcionário (Admin)");
            System.out.println("2 - Login Cliente");
            System.out.println("3 - Criar Conta (Cliente)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            int loginOp = lerInteiro();

            if (loginOp == 1) {
                System.out.print("Login: ");
                String login = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();
                if (funcionarioController.realizarLogin(login, senha)) {
                    menuPrincipal();
                } else {
                    System.out.println("Login inválido!");
                }
            } else if (loginOp == 2) {
                System.out.print("Login: ");
                String login = scanner.nextLine();
                System.out.print("Senha: ");
                String senha = scanner.nextLine();
                Cliente clienteLogado = clienteController.realizarLogin(login, senha);
                if (clienteLogado != null) {
                    menuCliente(clienteLogado);
                } else {
                    System.out.println("Login inválido!");
                }
            } else if (loginOp == 3) {
                System.out.println("\n--- CRIAR NOVA CONTA ---");
                System.out.print("Escolha seu Login (Nome de usuário): ");
                String novoLogin = scanner.nextLine();
                System.out.print("Escolha sua Senha: ");
                String novaSenha = scanner.nextLine();
                
                // Usamos o login como nome também para o cadastro simplificado
                clienteController.adicionarCliente(novoLogin, "", "", "", novoLogin, novaSenha);
            } else if (loginOp == 0) {
                sair = true;
                System.out.println("Até logo!");
            }
        }
    }

    private static void menuPrincipal() {
        int opcao = -1;
        do {
            System.out.println("\n========================================");
            System.out.println("             MENU PRINCIPAL             ");
            System.out.println("========================================");
            System.out.println("1 - Gerenciar Medicamentos (Estoque)");
            System.out.println("2 - Gerenciar Clientes");
            System.out.println("3 - Gerenciar Fornecedores");
            System.out.println("4 - Gerenciar Funcionários");
            System.out.println("5 - Frente de Vendas (PDV)");
            System.out.println("0 - Logout");
            System.out.print("Escolha uma opção: ");
            
            opcao = lerInteiro();

            switch (opcao) {
                case 1: submenuMedicamentos(); break;
                case 2: submenuClientes(); break;
                case 3: submenuFornecedores(); break;
                case 4: submenuFuncionarios(); break;
                case 5: submenuVendas(); break;
                case 0: break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void menuCliente(Cliente cliente) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n========================================");
            System.out.println("      ÁREA DO CLIENTE: " + cliente.getNome());
            System.out.println("========================================");
            System.out.println("1 - Ver Medicamentos Disponíveis");
            System.out.println("2 - Comprar Medicamento");
            System.out.println("0 - Logout");
            System.out.print("Opção: ");
            op = lerInteiro();

            if (op == 1) {
                medicamentoController.exibirEstoque();
            } else if (op == 2) {
                System.out.print("Digite o nome do medicamento: ");
                String nomeMed = scanner.nextLine();
                System.out.print("Quantidade desejada: ");
                int qtd = lerInteiro();
                String data = "10/10/2026";
                vendaController.realizarVenda(data, cliente.getNome(), nomeMed, qtd);
            }
        }
    }

    private static void submenuMedicamentos() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n>> MÓDULO: MEDICAMENTOS");
            System.out.println("1 - Cadastrar Novo Medicamento");
            System.out.println("2 - Visualizar Estoque");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            if (op == 1) {
                System.out.print("Nome do Medicamento: ");
                String nome = scanner.nextLine();
                System.out.print("Laboratório: ");
                String lab = scanner.nextLine();
                System.out.print("Preço de Venda: R$ ");
                double preco = lerDouble();
                System.out.print("Quantidade Inicial em Estoque: ");
                int qtd = lerInteiro();
                System.out.print("Data de Validade (DD/MM/AAAA): ");
                String validade = scanner.nextLine();

                medicamentoController.adicionarMedicamento(nome, lab, preco, qtd, validade);
            } else if (op == 2) {
                medicamentoController.exibirEstoque(); 
            }
        }
    }

    private static void submenuClientes() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n>> MÓDULO: CLIENTES");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Listar Clientes");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            if (op == 1) {
                System.out.print("Nome Completo: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Telefone: ");
                String tel = scanner.nextLine();
                System.out.print("E-mail: ");
                String email = scanner.nextLine();
                System.out.print("Login: ");
                String log = scanner.nextLine();
                System.out.print("Senha: ");
                String sen = scanner.nextLine();

                clienteController.adicionarCliente(nome, cpf, tel, email, log, sen);
            } else if (op == 2) {
                clienteController.exibirClientes();
            }
        }
    }

    private static void submenuFornecedores() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n>> MÓDULO: FORNECEDORES");
            System.out.println("1 - Cadastrar Fornecedor");
            System.out.println("2 - Listar Fornecedores");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            if (op == 1) {
                System.out.print("Razão Social: ");
                String razao = scanner.nextLine();
                System.out.print("CNPJ: ");
                String cnpj = scanner.nextLine();
                System.out.print("Telefone: ");
                String tel = scanner.nextLine();
                System.out.print("E-mail: ");
                String email = scanner.nextLine();

                fornecedorController.adicionarFornecedor(razao, cnpj, tel, email);
            } else if (op == 2) {
                fornecedorController.exibirFornecedores();
            }
        }
    }

    private static void submenuFuncionarios() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n>> MÓDULO: FUNCIONÁRIOS");
            System.out.println("1 - Cadastrar Funcionário");
            System.out.println("2 - Listar Funcionários");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            if (op == 1) {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Cargo: ");
                String cargo = scanner.nextLine();
                System.out.print("Username de Login: ");
                String login = scanner.nextLine();
                System.out.print("Senha de Acesso: ");
                String senha = scanner.nextLine();
                System.out.print("Nível de Acesso (ADMINISTRADOR/BALCONISTA): ");
                String nivel = scanner.nextLine().toUpperCase();

                funcionarioController.adicionarFuncionario(nome, cpf, cargo, login, senha, nivel);
            } else if (op == 2) {
                funcionarioController.exibirFuncionarios();
            }
        }
    }

    private static void submenuVendas() {
        int op = -1;
        while (op != 0) {
            System.out.println("\n>> MÓDULO: FRENTE DE VENDAS (PDV)");
            System.out.println("1 - Realizar Nova Venda");
            System.out.println("2 - Histórico de Vendas");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            op = lerInteiro();

            if (op == 1) {
                System.out.print("Nome do Cliente: ");
                String cliente = scanner.nextLine();
                System.out.print("Nome do Medicamento: ");
                String nomeMed = scanner.nextLine();
                System.out.print("Quantidade: ");
                int qtd = lerInteiro();
                System.out.print("Data da Venda (DD/MM/AAAA): ");
                String data = scanner.nextLine();

                vendaController.realizarVenda(data, cliente, nomeMed, qtd);
            } else if (op == 2) {
                vendaController.listarTodasVendas();
            }
        }
    }

    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.print("Entrada inválida! Digite um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.print("Entrada inválida! Digite um valor numérico (ex: 15.90): ");
            }
        }
    }

    private static void carregarDadosIniciais() {
        funcionarioController.adicionarFuncionario("Administrador Geral", "000.000.000-00", "Gerente", "admin", "admin123", "ADMINISTRADOR");
        
        medicamentoRepository.salvar(new Medicamento(null, "Paracetamol", "Medley", 12.50, 50, "12/2027"));
        medicamentoRepository.salvar(new Medicamento(null, "Amoxicilina", "EMS", 45.00, 20, "08/2026"));
        medicamentoRepository.salvar(new Medicamento(null, "Ibuprofeno", "Eurofarma", 18.20, 35, "05/2027"));

        clienteRepository.salvar(new Cliente(null, "João Antônio", "111.222.333-44", "83 99999-9999", "joao@email.com", "joao", "123"));
    }
}
