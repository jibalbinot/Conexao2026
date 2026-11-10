package codigo;

import codigo.controller.UsuarioController;
import codigo.model.IUsuarioDAO;
import codigo.model.UsuarioDAO;
import codigo.view.UsuarioView;

public class Main {
    public static void main(String[] args) {
        // Instancia as dependências do padrão MVC
        IUsuarioDAO dao = new UsuarioDAO();
        UsuarioView view = new UsuarioView();
        
        // Passa o Model e a View para o Controller
        UsuarioController controller = new UsuarioController(dao, view);

        // Inicia o sistema
        controller.iniciarAplicacao();
    }
}