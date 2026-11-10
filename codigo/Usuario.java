package codigo;

/**
 * A classe Usuario é um JavaBean (ou Entidade).
 * Sua função é encapsular os dados de um usuário (id, nome e senha),
 * facilitando o transporte dessas informações entre as camadas do sistema.
 */
public class Usuario {
    
    private int id;
    private String nome;
    private String senha;

    // Construtor padrão (vazio) obrigatório em JavaBeans
    public Usuario() {
    }

    // Construtor útil para criar um novo usuário (sem ID, pois o banco gera sozinho)
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    // Construtor completo (útil ao buscar dados já existentes no banco)
    public Usuario(int id, String nome, String senha) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    // Métodos Getter e Setter para acessar e modificar os atributos privados
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}