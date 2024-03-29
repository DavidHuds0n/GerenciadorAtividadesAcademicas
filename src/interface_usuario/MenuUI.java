package interface_usuario;

import java.util.Scanner;
import dados.*;
import negocios.*;

public class MenuUI {
    private ProfessorRepository professorRepository;
    private AlunoRepository alunoRepository;
    private CoordenadorRepository coordenadorRepository;
    private Scanner scanner;

    public MenuUI(ProfessorRepository professorRepository, AlunoRepository alunoRepository, CoordenadorRepository coordenadorRepository) {
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
        this.coordenadorRepository = coordenadorRepository;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        boolean sair = false;

        while (!sair) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    realizarLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    public void realizarLogin() {
        System.out.println("Selecione o tipo de usuário:");
        System.out.println("1. Professor");
        System.out.println("2. Aluno");
        System.out.println("3. Coordenador");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.print("Nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        boolean loginSucesso = false;
        switch (opcao) {
            case 1:
                loginSucesso = professorRepository.autenticarProfessor(username, password);
                break;
            case 2:
                loginSucesso = alunoRepository.autenticarAluno(username, password);
                break;
            case 3:
                loginSucesso = coordenadorRepository.autenticarCoordenador(username, password);
                break;
            default:
                System.out.println("Opção inválida!");
        }

        if (loginSucesso) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + username + "!");
        } else {
            System.out.println("Nome de usuário ou senha incorretos. Por favor, tente novamente.");
        }
    }

    public void cadastrarUsuario() {
        System.out.println("Selecione o tipo de usuário a cadastrar:");
        System.out.println("1. Professor");
        System.out.println("2. Aluno");
        System.out.println("3. Coordenador");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        System.out.print("Nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        boolean usuarioExiste = false;
        switch (opcao) {
            case 1:
                usuarioExiste = professorRepository.buscarProfessor(username) != null;
                break;
            case 2:
                usuarioExiste = alunoRepository.buscarAluno(username) != null;
                break;
            case 3:
                usuarioExiste = coordenadorRepository.buscarCoordenador(username) != null;
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (usuarioExiste) {
            System.out.println("Erro: Já existe um usuário com esse nome cadastrado.");
            return;
        }

        switch (opcao) {
            case 1:
                Professor professor = new Professor(username, password);
                professorRepository.cadastrarProfessor(professor);
                System.out.println("Professor cadastrado com sucesso!");
                break;
            case 2:
                Aluno aluno = new Aluno(username, password);
                alunoRepository.cadastrarAluno(aluno);
                System.out.println("Aluno cadastrado com sucesso!");
                break;
            case 3:
                Coordenador coordenador = new Coordenador(username, password);
                coordenadorRepository.cadastrarCoordenador(coordenador);
                System.out.println("Coordenador cadastrado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
}