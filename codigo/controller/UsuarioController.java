package codigo.controller;

import codigo.model.IUsuarioDAO;
import codigo.model.Usuario;
import codigo.view.UsuarioView;
import java.util.List;

public class UsuarioController {
    private IUsuarioDAO dao;
    private UsuarioView view;

    public UsuarioController(IUsuarioDAO dao, UsuarioView view) {
        this.dao = dao;
        this.view = view;
    }

    public void iniciarAplicacao() {
        int opcao;
        do {
            opcao = view.exibirMenu();
            
            switch (opcao) {
                case 1:
                    inserir();
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    atualizar();
                    break;
                case 4:
                    deletar();
                    break;
                case 0:
                    view.exibirMensagem("Encerrando o sistema. Até logo!");
                    break;
                default:
                    view.exibirMensagem("Opção inválida! Escolha entre 0 e 4.");
            }
        } while (opcao != 0);

        view.fecharScanner();
    }

    private void inserir() {
        Usuario novo = view.coletarDadosUsuario();
        dao.inserir(novo);
        view.exibirMensagem("Sucesso! Usuário cadastrado no banco.");
    }

    private void listar() {
        List<Usuario> lista = dao.listarTodos();
        view.exibirListaUsuarios(lista);
    }

    private void atualizar() {
        int id = view.coletarId();
        Usuario atualizado = view.coletarDadosUsuario();
        atualizado.setId(id);
        
        dao.atualizar(atualizado);
        view.exibirMensagem("Processo de atualização concluído.");
    }

    private void deletar() {
        int id = view.coletarId();
        dao.deletar(id);
        view.exibirMensagem("Processo de exclusão concluído.");
    }
}