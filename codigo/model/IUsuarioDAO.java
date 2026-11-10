package codigo.model;

import java.util.List;

/**
 * Interface IUsuarioDAO define o contrato (as regras) para a persistência de usuários.
 * O uso de interfaces é uma excelente prática de orientação a objetos porque 
 * esconde os detalhes de implementação e permite trocar a tecnologia de banco de dados
 * (ex: MySQL para PostgreSQL) sem quebrar o restante da aplicação.
 */
public interface IUsuarioDAO {

    // Contrato para inserir um usuário
    void inserir(Usuario usuario);

    // Contrato para listar todos os usuários, retornando uma lista do tipo ArrayList
    List<Usuario> listarTodos();

    // Contrato para atualizar um usuário existente com base no ID
    void atualizar(Usuario usuario);

    // Contrato para deletar um usuário com base no ID
    void deletar(int id);
}