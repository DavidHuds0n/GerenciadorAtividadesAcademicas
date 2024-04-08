package interface_usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import dados.*;
import negocios.*;

public class MenuUI {
    private RepositorioUsuarios repositorioUsuarios;
    private Scanner scanner;

    public MenuUI(RepositorioUsuarios repositorioUsuarios) {
        this.repositorioUsuarios = repositorioUsuarios;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        boolean sair = false;

        int opcao = 0;

        while (!sair) {
            System.out.println("=========================================");
            System.out.println("Bem-vindo ao Sistema de Gestão Acadêmica!");
            System.out.println("=========================================");
            System.out.println("[Selecione uma opção]");
            System.out.println("1. Login");
            System.out.println("2. Cadastro");
            System.out.println("3. Encerrar o sistema");
            System.out.print("Opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // Limpar o buffer do scanner
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.print("Nome de usuário: ");
                    String username = scanner.nextLine();
                    System.out.print("Senha: ");
                    String password = scanner.nextLine();
                    if (realizarLogin(username, password)){
                        menuUsuario(username);
                    }
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

    public boolean realizarLogin(String username, String password) {

        boolean loginSucesso = repositorioUsuarios.autenticarUsuario(username, password);

        if (loginSucesso) {
            System.out.println("\nLogin bem-sucedido! Bem-vindo, " + username + "!");
            System.out.println("=========================================");
            return true;
        } else {
            System.out.println("\nNome de usuário ou senha incorretos. Por favor, tente novamente.");
            System.out.println("=========================================");
            return false;
        }
    }

    public void cadastrarUsuario() {
        boolean sair = false;

        while (!sair) {
            System.out.println("=========================================");
            System.out.println("[Selecione uma opção para cadastro]");
            System.out.println("1. Coordenador");
            System.out.println("2. Professor");
            System.out.println("3. Aluno");
            System.out.println("4. Sair");
            System.out.print("Opção: ");
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // Limpar o buffer do scanner
                continue;
            }

            System.out.println("=========================================");
            System.out.print("Nome de usuário: ");
            String username = scanner.nextLine();
            System.out.print("Senha: ");
            String password = scanner.nextLine();

            boolean usuarioExiste = repositorioUsuarios.buscarUsuario(username) != null;

            if (usuarioExiste) {
                System.out.println("Erro: Já existe um usuário com esse nome cadastrado.");
                System.out.println("=========================================\n");
                return;
            }
            switch (opcao) {
                case 1:
                    Usuario coordenador = new Coordenador(username, password);
                    repositorioUsuarios.cadastrarUsuario(coordenador);
                    repositorioUsuarios.addCoordenador(coordenador, username);
                    System.out.println("\nCoordenador cadastrado com sucesso!");
                    System.out.println("=========================================\n");
                    return;
                case 2:
                    Usuario professor = new Professor(username, password);
                    repositorioUsuarios.cadastrarUsuario(professor);
                    repositorioUsuarios.addProfessor(professor, username);
                    System.out.println("\nProfessor cadastrado com sucesso!");
                    System.out.println("=========================================\n");
                    return;
                case 3:
                    Usuario aluno = new Aluno(username, password);
                    repositorioUsuarios.cadastrarUsuario(aluno);
                    repositorioUsuarios.addAluno(aluno, username);
                    System.out.println("\nAluno cadastrado com sucesso!");
                    System.out.println("=========================================\n");
                    return;
                case 4:
                    sair = true;
                    return;
                default:
                    System.out.println("\nOpção inválida!");
                    System.out.println("=========================================\n");
            }

        }

    }

    public void menuUsuario(String usuario){
        boolean encontrado = false;
        for (String professor : repositorioUsuarios.getProfessores().keySet()) {
            if (professor.equals(usuario)) {
                opProfessor(repositorioUsuarios.getProfessores().get(usuario));
                encontrado = true;
                System.out.println("=========================================\n");
                break;
            }
        }

        if (!encontrado){
            for (String coordenador : repositorioUsuarios.getCoordenadores().keySet()) {
                if (coordenador.equals(usuario)) {
                    opCoordenador(repositorioUsuarios.getCoordenadores().get(usuario));
                    encontrado = true;
                    System.out.println("=========================================\n");
                    break;
                }
            }
        }

        if (!encontrado){
            for (String aluno : repositorioUsuarios.getAlunos().keySet()) {
                if (aluno.equals(usuario)) {
                    opAluno(repositorioUsuarios.getAlunos().get(usuario));
                    encontrado = true;
                    System.out.println("=========================================\n");
                    break;
                }
            }
        }
    }

    public void opCoordenador(Coordenador coordenador){
        boolean sair = false;

        while (!sair) {
            System.out.println("[Selecione uma opção]");
            System.out.println("1. Cadastrar uma disciplina");
            System.out.println("2. Cadastrar um aluno em uma disciplina");
            System.out.println("3. Sair para o menu");
            System.out.print("Opção: ");
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // Limpar o buffer do scanner
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("=========================================");
                    System.out.print("Insira o nome da disciplina: ");
                    String nome = scanner.nextLine();
                    System.out.print("Insira o código da disciplina: ");
                    String codigo = scanner.nextLine();
                    System.out.println("\n[Selecione o professor responsável pela disciplina]");
                    HashMap<String, Professor> professores = new HashMap<>(repositorioUsuarios.getProfessores());
                    int i = 0;
                    for (String username : professores.keySet()) {
                        System.out.println((i+1) + " - " + username);
                        i++;
                    }

                    int escolha;
                    do {
                        System.out.print("Opção: ");
                        escolha = scanner.nextInt();
                    } while (escolha < 1 || escolha > professores.size());

                    List<Professor> listaProfessores = new ArrayList<>(professores.values());
                    coordenador.criarDisciplina(nome, codigo, listaProfessores.get(escolha - 1));
                    System.out.println("=========================================");
                    break;

                case 2:
                    System.out.println("=========================================");
                    System.out.println("[Selecione o aluno que vai cadastrar]");
                    HashMap<String, Aluno> alunos = new HashMap<>(repositorioUsuarios.getAlunos());
                    int j = 0;
                    for (String aluno : alunos.keySet()) {
                        System.out.println((j+1) + " - " + aluno);
                        j++;
                    }
                    int num;
                    do {
                        System.out.print("Opção: ");
                        num = scanner.nextInt();
                    } while (num < 1 || num > alunos.size());

                    System.out.println("\n[Selecione disciplina]");
                    j = 0;
                    for (Disciplina disciplina : coordenador.getDisciplinas()) {
                        System.out.println((j+1) + " - " + disciplina.getNome());
                        j++;
                    }

                    int escolha2;
                    do {
                        System.out.print("Opção: ");
                        escolha2 = scanner.nextInt();
                    } while (escolha2 < 1 || escolha2 > alunos.size());

                    List<Aluno> listaAlunos = new ArrayList<>(alunos.values());
                    coordenador.cadastrarAlunoDisciplina(coordenador.getDisciplinas().get(escolha2 - 1), listaAlunos.get(num - 1));
                    System.out.println("=========================================");
                    break;

                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public void opProfessor(Professor professor){
        boolean sair = false;

        while (!sair) {
            System.out.println("[Selecione uma opção]");
            System.out.println("1. Visualizar disciplinas ministradas");
            System.out.println("2. Sair para o menu");
            System.out.print("Opção: ");
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // Limpar o buffer do scanner
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n[Escolha a disciplina que deseja acessar]");
                    List<Disciplina> disciplinasProf = professor.getDisciplinasMinistradas();

                    int i = 0;
                    for (Disciplina disciplina : disciplinasProf){
                        System.out.println((i+1) + " - " + disciplina.getNome());
                    }

                    int escolha;
                    do {
                        System.out.print("Opção: ");
                        escolha = scanner.nextInt();
                    } while (escolha < 1 || escolha > disciplinasProf.size());

                    int op;
                    do {
                        op = menuProf(professor, disciplinasProf.get(escolha-1));
                    } while (op != 6);

                    break;

                case 2:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public int menuProf(Professor professor, Disciplina disciplina) {
        System.out.println("=========================================");
        System.out.println("-> Disciplina atual: " + disciplina.getNome());
        System.out.println("\nSelecione uma opção: ");
        System.out.println("1. Consultar atividades");
        System.out.println("2. Inserir uma nova atividade");
        System.out.println("3. Definir a data da prova");
        System.out.println("4. Consultar a data da prova");
        System.out.println("5. Atribuir notas da prova");
        System.out.println("6. Escolher outra disciplina");

        int opcao;
        do {
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
        } while (opcao < 1 || opcao > 6);
        scanner.nextLine(); // Consumir a nova linha pendente

        switch (opcao) {
            case 1:
                System.out.println("=========================================");
                Set<String> atividades = disciplina.getAtividades().keySet();
                int i = 0;
                for (String atividade : atividades) {
                    System.out.println((i + 1) + " - " + atividade + "; Data de entrega: " + disciplina.getAtividades().get(atividade));
                    i++;
                }
                break;

            case 2:
                System.out.println("=========================================");
                System.out.print("Titulo da atividade: ");
                String nome = scanner.nextLine();
                System.out.println("\n[Definição da data de entrega]");
                System.out.print("Dia: ");
                int dia = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha pendente
                System.out.print("Mês: ");
                int mes = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha pendente
                System.out.print("Ano: ");
                int ano = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha pendente
                LocalDate data = LocalDate.of(ano, mes, dia);
                professor.criarAtividade(disciplina, nome, data);
                break;

            case 3:
                System.out.println("=========================================");
                System.out.println("[Definição da data da prova]");
                System.out.print("Dia: ");
                int dia2 = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha pendente
                System.out.print("Mês: ");
                int mes2 = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha pendente
                System.out.print("Ano: ");
                int ano2 = scanner.nextInt();
                scanner.nextLine(); // Consumir a nova linha pendente
                LocalDate data2 = LocalDate.of(ano2, mes2, dia2);
                professor.definirDataProva(disciplina, data2);
                break;

            case 4:
                System.out.println("=========================================");
                LocalDate dataProva = disciplina.getDatasProvas();
                if (dataProva != null) {
                    System.out.println("Data da prova: " + dataProva);
                } else {
                    System.out.println("A data da prova ainda não está definida.");
                }
                break;

            case 5:
                System.out.println("=========================================");
                System.out.println("\n[Selecione o aluno para atribuir nota]");

                // Imprimir lista de alunos matriculados
                List<Aluno> alunos = disciplina.getAlunosMatriculados();
                int index = 1;
                for (Aluno aluno : alunos) {
                    System.out.println(index + ". " + aluno.getUsername());
                    index++;
                }

                // Permitir que o professor selecione um aluno
                System.out.print("Opção: ");
                int opcaoAluno = scanner.nextInt();
                if (opcaoAluno < 1 || opcaoAluno > alunos.size()) {
                    System.out.println("Opção inválida!");
                } else {
                    Aluno alunoSelecionado = alunos.get(opcaoAluno - 1);
                    System.out.print("Nota: ");
                    double nota = scanner.nextDouble();
                    professor.inserirNota(disciplina, alunoSelecionado, nota);
                }
                break;

            case 6:
                System.out.println("=========================================");
                break;
        }

        return opcao;
    }

    public void opAluno(Aluno aluno) {
        boolean sair = false;

        while (!sair) {
            System.out.println("[Selecione uma opção]");
            System.out.println("1. Visualizar disciplinas matriculadas");
            System.out.println("2. Sair para o menu");
            System.out.print("Opção: ");
            int opcao;
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número válido.");
                scanner.next(); // Limpar o buffer do scanner
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n[Escolha a disciplina que deseja acessar]");
                    List<Disciplina> disciplinasMatriculadas = aluno.getDisciplinasMatriculadas();

                    int i = 0;
                    for (Disciplina disciplina : disciplinasMatriculadas) {
                        System.out.println((i + 1) + " - " + disciplina.getNome());
                    }

                    int escolha;
                    do {
                        System.out.print("Opção: ");
                        escolha = scanner.nextInt();
                    } while (escolha < 1 || escolha > disciplinasMatriculadas.size());

                    int op;
                    do {
                        op = menuAluno(aluno, disciplinasMatriculadas.get(escolha - 1));
                    } while (op != 4);

                    break;

                case 2:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public int menuAluno(Aluno aluno, Disciplina disciplina) {
        System.out.println("=========================================");
        System.out.println("-> Disciplina atual: " + disciplina.getNome());
        System.out.println("\nSelecione uma opção:");
        System.out.println("1. Consultar atividades");
        System.out.println("2. Consultar datas de provas");
        System.out.println("3. Consultar nota na disciplina");
        System.out.println("4. Voltar");

        int opcao;
        do {
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
        } while (opcao < 1 || opcao > 4);
        scanner.nextLine(); // Consumir a nova linha pendente

        switch (opcao) {
            case 1:
                System.out.println("=========================================");
                Set<String> atividades = disciplina.getAtividades().keySet();
                int i = 0;
                for (String atividade : atividades) {
                    System.out.println((i + 1) + " - " + atividade + "; Data de entrega: " + disciplina.getAtividades().get(atividade));
                    i++;
                }
                break;

            case 2:
                System.out.println("=========================================");
                LocalDate dataProva = aluno.consultarDatasProvas(disciplina);
                if (dataProva != null) {
                    System.out.println("Data da prova: " + dataProva);
                } else {
                    System.out.println("A data da prova ainda não está definida.");
                }
                break;

            case 3:
                System.out.println("=========================================");
                Double nota = aluno.consultarNota(disciplina);
                if (nota != null) {
                    System.out.println("Nota na disciplina: " + nota);
                } else {
                    System.out.println("Nota ainda não atribuída nesta disciplina.");
                }
                break;

            case 4:
                System.out.println("=========================================");
                break;
        }

        return opcao;
    }
}
