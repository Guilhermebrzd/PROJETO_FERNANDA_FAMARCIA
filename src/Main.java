package br.com.farmacia.view;

import java.util.Scanner;
import br.com.farmacia.controller.VendaService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendaService moduloGuiga = new VendaService();

        int opcao = 0;
        int contadorIds = 1;

        while (opcao != 6) {
            System.out.println("\n=== MÓDULO DA FARMÁCIA (PARTE DO GUIGA) ===");
            System.out.println("1 - Realizar Nova Venda e Emitir Comprovante");
            System.out.println("2 - Consultar Estoque Atual");
            System.out.println("3 - Histórico de Vendas (CRUD - Listar)");
            System.out.println("4 - Cancelar Venda (CRUD - Deletar)");
            System.out.println("5 - Relatório de Lucro Mensal");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Por favor, digite um número!");
                scanner.next();
                continue;
            }

            if (opcao == 1) {
                System.out.print("Nome do Cliente: ");
                String cliente = scanner.nextLine();
                System.out.print("Data: ");
                String data = scanner.nextLine();
                System.out.print("Medicamento: ");
                String medicamento = scanner.nextLine();
                System.out.print("Quantidade: ");
                int qtd = scanner.nextInt();

                moduloGuiga.realizarVenda(contadorIds, data, cliente, medicamento, qtd);
                contadorIds++;

            } else if (opcao == 2) {
                moduloGuiga.exibirEstoque();

            } else if (opcao == 3) {
                moduloGuiga.listarTodasVendas();

            } else if (opcao == 4) {
                System.out.print("Digite o ID da venda a ser cancelada: ");
                int idCancelado = scanner.nextInt();
                moduloGuiga.cancelarVenda(idCancelado);

            } else if (opcao == 5) {
                moduloGuiga.exibirRelatorioLucro();

            } else if (opcao == 6) {
                System.out.println("Encerrando módulo...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}