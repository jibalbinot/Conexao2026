package codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        // Loop do menu interativo: continua rodando até que o usuário escolha sair (opção 0)
        do {
            System.out.println("\n=================================");
            System.out.println("   MENU - GERENCIADOR DE USUÁRIOS");
            System.out.println("=================================");
            System.out.println("1. Inserir novo usuário");
            System.out.println("2. Listar todos os usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                // Lê a opção digitada pelo usuário no console
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Por favor, digite um número.");
                continue;
            }

            // Direciona para a funcionalidade escolhida
            switch (opcao) {
                case 1:
                    inserirUsuarioConsole(scanner);
                    break;
                case 2:
                    listarUsuariosConsole();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente entre 0 e 2.");
            }

        } while (opcao != 0);

        // Fecha o scanner ao encerrar o programa para evitar vazamento de recursos
        scanner.close();
    }

    /**
     * Método responsável por coletar dados via console, instanciar um JavaBean Usuario
     * e realizar a inserção no banco de dados.
     */
    private static void inserirUsuarioConsole(Scanner scanner) {
        System.out.println("\n--- CADASTRO DE NOVO USUÁRIO ---");
        
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        // Criamos o objeto JavaBean preenchendo os dados coletados
        Usuario novoUsuario = new Usuario(nome, senha);

        String sql = "INSERT INTO usuario (nome, senha) VALUES (?, ?)";

        // Abrimos a conexão utilizando a ConnectionFactory (padrão Factory)
        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            // Preenche os parâmetros do comando SQL com os dados do JavaBean
            ps.setString(1, novoUsuario.getNome());
            ps.setString(2, novoUsuario.getSenha());

            // Executa a inserção no banco
            ps.execute();
            System.out.println("Sucesso! O usuário " + novoUsuario.getNome() + " foi cadastrado.");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário no banco de dados: " + e.getMessage());
        }
    }

    /**
     * Método responsável por consultar o banco de dados, converter os registros 
     * em objetos JavaBean e exibi-los formatados no console.
     */
    private static void listarUsuariosConsole() {
        System.out.println("\n--- LISTA DE USUÁRIOS CADASTRADOS ---");
        
        String sql = "SELECT * FROM usuario";

        // Conecta ao banco e executa a consulta
        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean encontrouRegistros = false;

            // Percorre cada linha retornada pelo banco de dados
            while (rs.next()) {
                encontrouRegistros = true;

                // Mapeia os dados do banco para um objeto JavaBean Usuario
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));

                // Exibe os dados utilizando os métodos get do JavaBean
                System.out.println(
                    "ID: " + usuario.getId() + " | " +
                    "Nome: " + usuario.getNome() + " | " +
                    "Senha: " + usuario.getSenha()
                );
            }

            if (!encontrouRegistros) {
                System.out.println("Nenhum usuário encontrado na tabela.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os usuários: " + e.getMessage());
        }
    }
}