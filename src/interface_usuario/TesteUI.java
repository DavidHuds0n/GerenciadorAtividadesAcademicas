package interface_usuario;

import negocios.*;
import dados.Disciplina;

import java.util.Scanner;

public class TesteUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criando objetos de teste: coordenador, professor e aluno
        Coordenador coordenador = new Coordenador("coordenador A", "senha");
        Professor professor = new Professor("professor X", "senha");
        Aluno aluno = new Aluno("aluno Z", "senha");

        // Menu principal
        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Testar métodos do Coordenador");
            System.out.println("2. Testar métodos do Professor");
            System.out.println("3. Testar métodos do Aluno");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                	testarMetodosCoordenador(coordenador, professor, aluno);
                    break;
                case 2:
                    testarMetodosProfessor(professor);
                    break;
                case 3:
                    testarMetodosAluno(aluno);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Método para testar os métodos do Coordenador
    private static void testarMetodosCoordenador(Coordenador coordenador, Professor professor, Aluno aluno) {
        // Aqui você pode chamar os métodos de teste do coordenador
        // Por exemplo:
        System.out.println("\n=== Testando métodos do Coordenador ===");
        Disciplina disciplina = coordenador.criarDisciplina("Programação Orientada a Objetos", "POO", professor);
        System.out.println("Disciplina criada: " + disciplina.getNome());
        System.out.println("Professor responsável: " + disciplina.getProfessorResponsavel().getUsername());
        
        // Adicionar alunos na disciplina
        coordenador.cadastrarAlunoDisciplina(disciplina, aluno);
        System.out.println("Aluno adicionado na disciplina: " + aluno.getUsername());
    }


    // Método para testar os métodos do Professor
    private static void testarMetodosProfessor(Professor professor) {
        // Aqui você pode chamar os métodos de teste do professor
        // Por exemplo:
        System.out.println("\n=== Testando métodos do Professor ===");
        System.out.println("Disciplina ministrada pelo professor: " + professor.getDisciplinasMinistradas().get(0).getNome());
    }

    // Método para testar os métodos do Aluno
    private static void testarMetodosAluno(Aluno aluno) {
        // Aqui você pode chamar os métodos de teste do aluno
        // Por exemplo:
        System.out.println("\n=== Testando métodos do Aluno ===");
        System.out.println("Aluno matriculado na disciplina: " + aluno.getDisciplinasMatriculadas().get(0).getNome());
    }
}

