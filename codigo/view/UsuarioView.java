package codigo.view;

import codigo.model.Usuario;
import java.util.List;
import java.util.Scanner;

public class UsuarioView {
    private Scanner scanner = new Scanner(System.in);

    public int exibirMenu() {
        System.out.println("\n=================================");
        System.out.println("   MENU - SISTEMA MVC (CONSOLE)");
        System.out.println("=================================");
        System.out.println("1. Inserir novo usuário");
        System.out.println("2. Listar todos os usuários");
        System.out.println("3. Atualizar usuário por ID");
        System.out.println("4. Deletar usuário por ID");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public Usuario coletarDadosUsuario() {
        System.out.println("\n--- INFORME OS DADOS ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        return new Usuario(nome, senha);
    }

    public int coletarId() {
        System.out.print("Digite o ID do usuário: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void exibirListaUsuarios(List<Usuario> usuarios) {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Senha: " + u.getSenha());
            }
        }
    }

    public void fecharScanner() {
        scanner.close();
    }
}