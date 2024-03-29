package dados;

import java.util.HashMap;
import java.util.Map;
import negocios.Aluno;

public class AlunoRepository {
    private Map<String, Aluno> alunos = new HashMap<>();

    public void cadastrarAluno(Aluno aluno) {
        String username = aluno.getUsername();
        alunos.put(username, aluno);
    }

    public Aluno buscarAluno(String username) {
        return alunos.get(username);
    }
    
    public boolean autenticarAluno(String username, String password) {
        Aluno aluno = buscarAluno(username);
        return aluno != null && aluno.getPassword().equals(password);
    }

}
