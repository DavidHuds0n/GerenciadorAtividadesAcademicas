package negocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dados.Disciplina;

public class Professor extends Usuario {
    private List<Disciplina> disciplinasMinistradas;

    public Professor(String username, String password) {
        super(username, password);
        this.disciplinasMinistradas = new ArrayList<>();
    }
    
    // Método para adicionar uma disciplina ministrada pelo professor
    public void adicionarDisciplinaMinistrada(Disciplina disciplina) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina nula é inválida.");
            }
            disciplinasMinistradas.add(disciplina);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar disciplina ministrada: " + e.getMessage());
        }
    }
    
    // Método para acessar todas as disciplinas ministradas pelo professor
    public List<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    // Método para inserir atividade em uma disciplina
    public void criarAtividade(Disciplina disciplina, String atividade, LocalDate data) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina nula é inválida.");
            }
            if (atividade == null || atividade.isEmpty()) {
                throw new IllegalArgumentException("Atividade vazia é inválida.");
            }
            if (data == null || data.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Data anterior a data atual é inválida.");
            }
            disciplina.inserirAtividade(atividade, data);
            System.out.println("Atividade criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar atividade: " + e.getMessage());
        }
    }

    // Método para definir data da prova em uma disciplina
    public void definirDataProva(Disciplina disciplina, LocalDate data) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina nula é inválida.");
            }
            if (data == null || data.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Data anterior a data atual é inválida.");
            }
            disciplina.inserirDataProva(data);
            System.out.println("Data da prova definida com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao definir data da prova: " + e.getMessage());
        }
    }

    // Método para inserir notas em uma disciplina
    public void inserirNota(Disciplina disciplina, Aluno aluno, Double nota) {
        try {
            if (disciplina == null || aluno == null || nota == null) {
                throw new IllegalArgumentException("Parâmetros inválidos.");
            }
            disciplina.inserirNota(aluno, nota);
            System.out.println("Nota inserida com sucesso para o aluno " + aluno.getUsername());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao inserir nota: " + e.getMessage());
        }
    }
}