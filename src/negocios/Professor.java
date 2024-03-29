package negocios;

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
        disciplinasMinistradas.add(disciplina);
    }
    
    // Método para acessar todas as disciplinas ministradas pelo professor
    public List<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    // Outros métodos específicos de um professor, se houver
}
