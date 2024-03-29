package interface_usuario;

import dados.*;

public class Main {
    public static void main(String[] args) {
        // Criando instâncias dos repositórios
        ProfessorRepository professorRepository = new ProfessorRepository();
        AlunoRepository alunoRepository = new AlunoRepository();
        CoordenadorRepository coordenadorRepository = new CoordenadorRepository();

        // Criando instância da classe de interface de usuário (MenuUI)
        MenuUI interfaceUsuario = new MenuUI(professorRepository, alunoRepository, coordenadorRepository);

        // Exibindo o menu
        interfaceUsuario.exibirMenu();
    }
}

