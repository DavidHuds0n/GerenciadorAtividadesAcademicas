package dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import negocios.Aluno;
import negocios.Professor;

public class Disciplina {
    private String nome;
    private String codigo;
    private Professor professorResponsavel;
    private List<Aluno> alunosMatriculados;
    private HashMap<String, LocalDate> atividades;
    private LocalDate dataProva;
    private HashMap<Aluno, Double> notasAlunos;

    public Disciplina(String nome, String codigo, Professor professorResponsavel) {
        this.nome = nome;
        this.codigo = codigo;
        this.professorResponsavel = professorResponsavel;
        this.alunosMatriculados = new ArrayList<>();
        this.atividades = new HashMap<>();
        this.notasAlunos = new HashMap<>();
    }

    // Getters e Setters para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getters e Setters para código
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Getters e Setters para professorResponsavel
    public Professor getProfessorResponsavel() {
        return professorResponsavel;
    }

    public void setProfessorResponsavel(Professor professorResponsavel) {
        this.professorResponsavel = professorResponsavel;
    }
    
    // Método para retornar as atividades da disciplina
    public HashMap<String, LocalDate> getAtividades() {
        return this.atividades;
    }

    // Método para retornar as datas de provas da disciplina
    public LocalDate getDatasProvas() {
        return this.dataProva;
    }
    
    // Método para retornar os alunos matriculados na disciplina
    public List<Aluno> getAlunosMatriculados() {
        return this.alunosMatriculados;
    }
    
    // Método para adicionar aluno
    public void adicionarAluno(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("O aluno não pode ser nulo.");
        }
        this.alunosMatriculados.add(aluno);
    }

    // Método para remover aluno
    public void removerAluno(Aluno aluno) {
        if (!this.alunosMatriculados.contains(aluno)) {
            throw new IllegalArgumentException("O aluno não está matriculado nesta disciplina.");
        }
        this.alunosMatriculados.remove(aluno);
        this.notasAlunos.remove(aluno); // Remove as notas do aluno ao remover da disciplina
    }

    // Método para inserir atividade
    public void inserirAtividade(String atividade, LocalDate data) {
        if (atividade == null || data == null) {
            throw new IllegalArgumentException("Atividade ou data não podem ser nulas.");
        }
        this.atividades.put(atividade, data);
    }

    // Método para inserir data de prova
    public void inserirDataProva(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data da prova não pode ser nula.");
        }
        this.dataProva = data;
    }

    // Método para inserir notas
    public void inserirNota(Aluno aluno, Double nota) {
        try {
            if (aluno == null || nota == null) {
                throw new IllegalArgumentException("Aluno ou nota não podem ser nulos.");
            }
            if (!this.alunosMatriculados.contains(aluno)) {
                throw new IllegalArgumentException("O aluno não está matriculado nesta disciplina.");
            }
            this.notasAlunos.put(aluno, nota); // Adiciona a nota ao final da lista de notas do aluno
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao inserir nota: " + e.getMessage());
        }
    }

    // Método para consultar nota de um aluno
    public Double getNota(Aluno aluno) {
        try {
            if (aluno == null) {
                throw new IllegalArgumentException("Aluno não pode ser nulo.");
            }
            if (!this.notasAlunos.containsKey(aluno)) {
                throw new IllegalArgumentException("O aluno não possui notas registradas nesta disciplina.");
            }
            return this.notasAlunos.get(aluno);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao consultar nota: " + e.getMessage());
            return null;
        }
    }

}
