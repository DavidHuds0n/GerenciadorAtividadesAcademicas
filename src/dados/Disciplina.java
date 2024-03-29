package dados;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import negocios.*;

public class Disciplina {
    private String nome;
    private String codigo;
    private Professor professorResponsavel;
    private List<Aluno> alunosMatriculados;
    private Map<String, LocalDate> atividades;
    private List<LocalDate> datasProva;
    private Map<Aluno, Double> notasAlunos;

    public Disciplina(String nome, String codigo, Professor professorResponsavel) {
        this.nome = nome;
        this.codigo = codigo;
        this.professorResponsavel = professorResponsavel;
        this.alunosMatriculados = new ArrayList<>();
        this.atividades = new HashMap<>();
        this.datasProva = new ArrayList<>();
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

    // Métodos para manipulação de alunos matriculados
    public void adicionarAluno(Aluno aluno) {
        this.alunosMatriculados.add(aluno);
    }

    public void removerAluno(Aluno aluno) {
        this.alunosMatriculados.remove(aluno);
    }
}
