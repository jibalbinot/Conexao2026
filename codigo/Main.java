package codigo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // URL de conexão com o banco 'bb' rodando na porta padrão (3306)
        String url = "jdbc:mysql://localhost:3306/bb?useSSL=false&serverTimezone=UTC";
        String usuario = "root"; // Usuário padrão do MySQL
        String senha = "";      // Sem senha, conforme solicitado
        String comando;
        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println("Tentando conectar ao banco de dados MySQL...");

        try {
            // Carrega o driver JDBC (opcional em versões mais recentes, mas recomendado)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Estabelece a conexão
            Connection conexao = DriverManager.getConnection(url, usuario, senha);

            if (conexao != null) {
                System.out.println("Sucesso! Conexão com o banco 'bb' estabelecida com sucesso.");
                
                
                // inserir um novo registro

                //criei o comando
                comando = "insert into usuario (nome,senha) values (?,?)";
                
                //prapara o comando
                ps = conexao.prepareStatement(comando);
                
                //carrega os dados
                ps.setString(1, "Lindsay");
                ps.setString(2, "555");

                //executar o comando (sem retorno)
                //ps.execute();

                // selecionar e mostrar os registros da tabela usuario
                
                //crei o comando
                comando = "select * from usuario";

                //preparar o comando
                ps = conexao.prepareStatement(comando);

                //executa o comando (com retorno)
                rs = ps.executeQuery();

                //loop para mostrar na tela
                while (rs.next()) {
                    System.out.println(
                        rs.getInt("id")+" | "+
                        rs.getString("nome")+" | "+
                        rs.getString("senha") 
                    );
                }

                rs.close();
                ps.close();
                conexao.close();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver JDBC do MySQL não encontrado no projeto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro: Falha ao conectar ao banco de dados.");
            e.printStackTrace();
        }
    }
}