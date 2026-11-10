package codigo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe UsuarioDAO implementa o contrato definido em IUsuarioDAO.
 * Ela concentra toda a lógica de banco de dados (JDBC) isolada das regras do sistema.
 */
public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, senha) VALUES (?, ?)";

        // Usa o try-with-resources para garantir o fechamento seguro da conexão e do statement
        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            
            ps.execute();
            System.out.println("Sucesso! O usuário " + usuario.getNome() + " foi cadastrado.");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuario";
        
        // Criamos um ArrayList para armazenar os usuários vindos do banco
        List<Usuario> listaUsuarios = new ArrayList<>();

        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // Percorre cada linha do resultado do banco
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));

                // Adiciona o objeto preenchido à lista
                listaUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar os usuários: " + e.getMessage());
        }

        return listaUsuarios; // Retorna o ArrayList preenchido
    }

    @Override
    public void atualizar(Usuario usuario) {
        String sql = "UPDATE usuario SET nome = ?, senha = ? WHERE id = ?";

        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getId()); // Usa o ID como parâmetro de identificação

            // executeUpdate retorna a quantidade de linhas afetadas pela alteração
            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Sucesso! Usuário de ID " + usuario.getId() + " atualizado.");
            } else {
                System.out.println("Aviso: Nenhum usuário encontrado com o ID " + usuario.getId() + ".");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conexao = ConnectionFactory.conectar();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id); // Passa o ID do usuário que será excluído

            int linhasAfetadas = ps.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Sucesso! Usuário de ID " + id + " deletado do sistema.");
            } else {
                System.out.println("Aviso: Nenhum usuário encontrado com o ID " + id + ".");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
