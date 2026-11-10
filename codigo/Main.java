package codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String comando;
        
        // Declaramos as variáveis fora do try-with-resources ou do bloco para fechá-las depois,
        // mas vamos utilizar a própria conexão fornecida pela Factory.
        System.out.println("Tentando conectar ao banco de dados MySQL via ConnectionFactory...");

        // Usamos o try-with-resources do Java para garantir que a conexão, 
        // o PreparedStatement e o ResultSet sejam fechados automaticamente ao final do bloco.
        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement psInsert = conexao.prepareStatement("insert into usuario (nome, senha) values (?, ?)");
             PreparedStatement psSelect = conexao.prepareStatement("select * from usuario")) {

            if (conexao != null) {
                System.out.println("Sucesso! Conexão com o banco 'bb' estabelecida com sucesso.");

                // --- 1. INSERIR UM NOVO REGISTRO ---
                
                // Define os valores para os parâmetros (?'s) do comando de inserção
                psInsert.setString(1, "Adalberto");
                psInsert.setString(2, "777");

                // Executa o comando de inserção (sem retorno de dados)
                psInsert.execute();
                System.out.println("Novo usuário inserido com sucesso!\n");

                // --- 2. SELECIONAR E MOSTRAR OS REGISTROS DA TABELA ---
                
                // Executa a consulta SQL (com retorno de dados) guardando no ResultSet
                try (ResultSet rs = psSelect.executeQuery()) {
                    System.out.println("--- Lista de Usuários Cadastrados ---");
                    
                    // Loop para percorrer todas as linhas retornadas do banco de dados
                    while (rs.next()) {
                        System.out.println(
                            "ID: " + rs.getInt("id") + " | " +
                            "Nome: " + rs.getString("nome") + " | " +
                            "Senha: " + rs.getString("senha")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            // Captura erros relacionados à execução dos comandos SQL
            System.err.println("Erro durante a execução das operações no banco de dados.");
            e.printStackTrace();
        }
    }
}