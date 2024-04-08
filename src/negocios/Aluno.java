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
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina inválida.");
        }
        disciplinasMatriculadas.add(disciplina);
    }

    // Método para acessar todas as disciplinas em que o aluno está matriculado
    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    // Método para consultar as atividades da disciplina
    public List<String> consultarAtividades(Disciplina disciplina) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina inválida.");
        }
        return new ArrayList<>(disciplina.getAtividades().keySet());
    }

    // Método para consultar data de provas
    public LocalDate consultarDatasProvas(Disciplina disciplina) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina inválida.");
        }
        return disciplina.getDatasProvas();
    } 

    // Método para consultar a nota da disciplina:
    public Double consultarNota(Disciplina disciplina) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina inválida.");
        }
        return disciplina.getNota(this);
    }
}