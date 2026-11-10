package codigo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A classe ConnectionFactory implementa o padrão Factory.
 * O objetivo dela é centralizar a criação de conexões com o banco de dados,
 * evitando duplicação de código e escondendo os detalhes de configuração do restante do sistema.
 */
public class ConnectionFactory {

    // Constantes com as credenciais e o endereço do banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/bb?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    /**
     * Método responsável por criar e retornar uma nova conexão com o banco de dados.
     * É declarado como 'static' para que possamos chamá-lo diretamente pelo nome da classe,
     * sem precisar instanciar o ConnectionFactory (ex: ConnectionFactory.conectar()).
     * 
     * @return Um objeto Connection ativo ou lança uma exceção em caso de falha.
     */
    public static Connection conectar() {
        try {
            // Passo 1: Opcional nas versões mais recentes do JDBC, mas garante o carregamento explícito do driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Passo 2: Estabelece e retorna a conexão usando o DriverManager do JDBC
            return DriverManager.getConnection(URL, USUARIO, SENHA);
            
        } catch (ClassNotFoundException e) {
            // Lança uma RuntimeException caso o driver do MySQL não seja encontrado no projeto
            throw new RuntimeException("Erro: Driver JDBC do MySQL não encontrado no projeto.", e);
        } catch (SQLException e) {
            // Lança uma RuntimeException caso ocorra algum erro ao tentar se conectar ao servidor MySQL
            throw new RuntimeException("Erro: Falha ao conectar ao banco de dados.", e);
        }
    }
}