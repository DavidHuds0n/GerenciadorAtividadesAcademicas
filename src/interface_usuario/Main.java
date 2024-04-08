package interface_usuario;

import dados.*;

public class Main {
    public static void main(String[] args) {
        // Criando instâncias dos repositórios
    	RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

        // Criando instância da classe de interface de usuário (MenuUI)
        MenuUI interfaceUsuario = new MenuUI(repositorioUsuarios);

        // Exibindo o menu
        interfaceUsuario.exibirMenu();

    }
}

