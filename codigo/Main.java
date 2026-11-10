package codigo;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Polimorfismo: instanciamos a implementação usando o tipo da Interface
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        int opcao = -1;

        do {
            System.out.println("\n=================================");
            System.out.println("   MENU - GERENCIADOR DE USUÁRIOS");
            System.out.println("=================================");
            System.out.println("1. Inserir novo usuário");
            System.out.println("2. Listar todos os usuários");
            System.out.println("3. Atualizar usuário por ID");
            System.out.println("4. Deletar usuário por ID");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Por favor, digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    inserir(scanner, usuarioDAO);
                    break;
                case 2:
                    listar(usuarioDAO);
                    break;
                case 3:
                    atualizar(scanner, usuarioDAO);
                    break;
                case 4:
                    deletar(scanner, usuarioDAO);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha entre 0 e 4.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void inserir(Scanner scanner, IUsuarioDAO dao) {
        System.out.println("\n--- CADASTRAR NOVO USUÁRIO ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = new Usuario(nome, senha);
        dao.inserir(usuario);
    }

    private static void listar(IUsuarioDAO dao) {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        List<Usuario> usuarios = dao.listarTodos();

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            // Percorre o ArrayList retornado pelo DAO
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Senha: " + u.getSenha());
            }
        }
    }

    private static void atualizar(Scanner scanner, IUsuarioDAO dao) {
        System.out.println("\n--- ATUALIZAR USUÁRIO ---");
        System.out.print("Digite o ID do usuário que deseja atualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = new Usuario(id, nome, senha);
        dao.atualizar(usuario);
    }

    private static void deletar(Scanner scanner, IUsuarioDAO dao) {
        System.out.println("\n--- DELETAR USUÁRIO ---");
        System.out.print("Digite o ID do usuário que deseja remover: ");
        int id = Integer.parseInt(scanner.nextLine());

        dao.deletar(id);
    }
}