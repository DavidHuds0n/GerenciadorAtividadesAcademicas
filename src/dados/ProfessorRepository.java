package dados;

import java.util.HashMap;
import java.util.Map;
import negocios.Professor;

public class ProfessorRepository {
    private Map<String, Professor> professores = new HashMap<>();

    public void cadastrarProfessor(Professor professor) {
        String username = professor.getUsername();
        professores.put(username, professor);
    }

    public Professor buscarProfessor(String username) {
        return professores.get(username);
    }
    
    public boolean autenticarProfessor(String username, String password) {
        Professor professor = buscarProfessor(username);
        return professor != null && professor.getPassword().equals(password);
    }

}


