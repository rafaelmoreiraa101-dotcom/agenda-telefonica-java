package main;

import dao.ContatoDAO;
import model.Contato;

import java.util.ArrayList;
import java.util.Scanner;

public class AgendaTeste {

    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String AZUL = "\u001B[34m";
    public static final String AMARELO = "\u001B[33m";
    public static final String CIANO = "\u001B[36m";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ContatoDAO dao = new ContatoDAO();

        int opcao = 0;

        boasVindas();

        do {
            menu();

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException erro) {
                mensagemErro("Digite apenas numeros.");
                continue;
            }

            switch (opcao) {

                case 1:
                    adicionarContato(scanner, dao);
                    pausar(scanner);
                    break;

                case 2:
                    listarContatos(dao);
                    pausar(scanner);
                    break;

                case 3:
                    buscarContato(scanner, dao);
                    pausar(scanner);
                    break;

                case 4:
                    atualizarContato(scanner, dao);
                    pausar(scanner);
                    break;

                case 5:
                    removerContato(scanner, dao);
                    pausar(scanner);
                    break;

                case 6:
                    mensagemSucesso("Sistema encerrado. Ate logo!");
                    break;

                default:
                    mensagemErro("Opcao invalida. Escolha uma opcao do menu.");
                    pausar(scanner);
            }

        } while (opcao != 6);

        scanner.close();
    }

    public static void boasVindas() {
        System.out.println(AZUL + "========================================" + RESET);
        System.out.println(VERDE + "        BEM-VINDO A SUA AGENDA" + RESET);
        System.out.println(CIANO + "      Sistema de Agenda Telefonica" + RESET);
        System.out.println(AZUL + "========================================" + RESET);
    }

    public static void menu() {
        System.out.println(AZUL + "\n========================================" + RESET);
        System.out.println(CIANO + "              MENU PRINCIPAL" + RESET);
        System.out.println(AZUL + "========================================" + RESET);
        System.out.println("1 - Adicionar novo contato");
        System.out.println("2 - Listar todos os contatos");
        System.out.println("3 - Buscar contato pelo nome");
        System.out.println("4 - Atualizar contato");
        System.out.println("5 - Remover contato");
        System.out.println("6 - Sair do sistema");
        System.out.print(AMARELO + "\nEscolha uma opcao: " + RESET);
    }

    public static void adicionarContato(Scanner scanner, ContatoDAO dao) {
        Contato contato = new Contato();

        System.out.println(CIANO + "\n--- Cadastro de Contato ---" + RESET);

        System.out.print("Nome: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            mensagemErro("O nome e obrigatorio.");
            return;
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine().trim();

        if (telefone.isEmpty()) {
            mensagemErro("O telefone e obrigatorio.");
            return;
        }

        if (dao.telefoneExiste(telefone)) {
            mensagemErro("Este telefone ja esta cadastrado.");
            return;
        }

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        if (!email.contains("@") || !email.contains(".")) {
            mensagemErro("Digite um email valido.");
            return;
        }

        contato.setNome(nome);
        contato.setTelefone(telefone);
        contato.setEmail(email);

        dao.adicionarContato(contato);
    }

    public static void listarContatos(ContatoDAO dao) {
        ArrayList<Contato> contatos = dao.listarContatos();

        System.out.println(CIANO + "\n--- Lista de Contatos ---" + RESET);

        if (contatos.isEmpty()) {
            mensagemAviso("Nenhum contato cadastrado no momento.");
            return;
        }

        System.out.println(
                AMARELO +
                "\nTotal de contatos: " + contatos.size() +
                RESET
        );

        mostrarLista(contatos);
    }

    public static void buscarContato(Scanner scanner, ContatoDAO dao) {
        System.out.println(CIANO + "\n--- Busca de Contato ---" + RESET);

        System.out.print("Digite o nome para buscar: ");
        String nomeBusca = scanner.nextLine().trim();

        if (nomeBusca.isEmpty()) {
            mensagemErro("Digite um nome para realizar a busca.");
            return;
        }

        ArrayList<Contato> encontrados = dao.buscarContato(nomeBusca);

        if (encontrados.isEmpty()) {
            mensagemAviso("Nenhum contato encontrado com esse nome.");
            return;
        }

        System.out.println(
                AMARELO +
                "\nContatos encontrados: " + encontrados.size() +
                RESET
        );

        mostrarLista(encontrados);
    }

    public static void atualizarContato(Scanner scanner, ContatoDAO dao) {
        Contato atualizado = new Contato();

        System.out.println(CIANO + "\n--- Atualizar Contato ---" + RESET);

        System.out.print("Digite o ID do contato: ");

        try {
            atualizado.setId(Integer.parseInt(scanner.nextLine()));
        } catch (NumberFormatException erro) {
            mensagemErro("ID invalido. Digite apenas numeros.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine().trim();

        if (nome.isEmpty()) {
            mensagemErro("O nome e obrigatorio.");
            return;
        }

        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine().trim();

        if (telefone.isEmpty()) {
            mensagemErro("O telefone e obrigatorio.");
            return;
        }

        System.out.print("Novo email: ");
        String email = scanner.nextLine().trim();

        if (!email.contains("@") || !email.contains(".")) {
            mensagemErro("Digite um email valido.");
            return;
        }

        atualizado.setNome(nome);
        atualizado.setTelefone(telefone);
        atualizado.setEmail(email);

        dao.atualizarContato(atualizado);
    }

    public static void removerContato(Scanner scanner, ContatoDAO dao) {

        ArrayList<Contato> contatos = dao.listarContatos();

        System.out.println(CIANO + "\n--- Remover Contato ---" + RESET);

        if (contatos.isEmpty()) {
            mensagemAviso("Nenhum contato cadastrado.");
            return;
        }

        mostrarLista(contatos);

        System.out.print(
                AMARELO +
                "\nDigite o ID do contato que deseja remover: " +
                RESET
        );

        try {
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print(
                    AMARELO +
                    "\nTem certeza que deseja remover? (S/N): " +
                    RESET
            );

            String confirmacao = scanner.nextLine();

            if (!confirmacao.equalsIgnoreCase("S")) {
                mensagemAviso("Remocao cancelada.");
                return;
            }

            dao.removerContato(id);

        } catch (NumberFormatException erro) {
            mensagemErro("ID invalido. Digite apenas numeros.");
        }
    }

    public static void mostrarLista(ArrayList<Contato> contatos) {
        for (Contato c : contatos) {
            System.out.println(AZUL + "\n----------------------------------------" + RESET);
            System.out.println(VERDE + "ID: " + RESET + c.getId());
            System.out.println(VERDE + "Nome: " + RESET + c.getNome());
            System.out.println(VERDE + "Telefone: " + RESET + c.getTelefone());
            System.out.println(VERDE + "Email: " + RESET + c.getEmail());
        }
    }

    public static void pausar(Scanner scanner) {
        System.out.print(AMARELO + "\nPressione ENTER para continuar..." + RESET);
        scanner.nextLine();
    }

    public static void mensagemSucesso(String texto) {
        System.out.println(VERDE + "\n[SUCESSO] " + texto + RESET);
    }

    public static void mensagemErro(String texto) {
        System.out.println(VERMELHO + "\n[ERRO] " + texto + RESET);
    }

    public static void mensagemAviso(String texto) {
        System.out.println(AMARELO + "\n[AVISO] " + texto + RESET);
    }
}