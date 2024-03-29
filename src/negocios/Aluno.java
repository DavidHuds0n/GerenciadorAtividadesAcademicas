package negocios;

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
        disciplinasMatriculadas.add(disciplina);
    }

    // Método para acessar todas as disciplinas em que o aluno está matriculado
    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    // Outros métodos específicos de um aluno, se houver
}


