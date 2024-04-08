package negocios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dados.Disciplina;

public class Aluno extends Usuario {
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String username, String password) {
        super(username, password);
        this.disciplinasMatriculadas = new ArrayList<>();
    }

    // Método para adicionar uma disciplina em que o aluno está matriculado
    public void adicionarDisciplinaMatriculada(Disciplina disciplina) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina nula é inválida.");
            }
            disciplinasMatriculadas.add(disciplina);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao adicionar disciplina: " + e.getMessage());
        }
    }

    // Método para acessar todas as disciplinas em que o aluno está matriculado
    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    // Método para consultar as atividades da disciplina
    public List<String> consultarAtividades(Disciplina disciplina) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina nula é inválida.");
            }
            return new ArrayList<>(disciplina.getAtividades().keySet());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao consultar atividades: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método para consultar data de provas
    public LocalDate consultarDatasProvas(Disciplina disciplina) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina nula é inválida.");
            }
            return disciplina.getDatasProvas();
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao consultar datas de provas: " + e.getMessage());
            return null;
        }
    } 

    // Método para consultar a nota da disciplina:
    public Double consultarNota(Disciplina disciplina) {
        try {
            if (disciplina == null) {
                throw new IllegalArgumentException("Disciplina nula é inválida.");
            }
            return disciplina.getNota(this);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao consultar nota: " + e.getMessage());
            return null;
        }
    }
}
