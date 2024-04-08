package dados;

import java.util.HashMap;
import java.util.Map;
import negocios.*;

public class RepositorioUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<String, Professor> professores = new HashMap<>();
    private Map<String, Coordenador> coordenadores = new HashMap<>();
    private Map<String, Aluno> alunos = new HashMap<>();

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }
        String username = usuario.getUsername();
        if (usuarios.containsKey(username)) {
            throw new IllegalArgumentException("Já existe um usuário com esse nome cadastrado.");
        }
        usuarios.put(username, usuario);
    }

    public Usuario buscarUsuario(String username) {
        return usuarios.get(username);
    }

    public boolean autenticarUsuario(String username, String password) {
        Usuario usuario = buscarUsuario(username);
        return usuario != null && usuario.getPassword().equals(password);
    }

    public void addProfessor(Usuario professor, String nome) {
        if (!(professor instanceof Professor)) {
            throw new IllegalArgumentException("O usuário informado não é um professor.");
        }
        professores.put(nome, (Professor) professor);
    }

    public Map<String, Professor> getProfessores() {
        return professores;
    }

    public void addCoordenador(Usuario coordenador, String nome) {
        if (!(coordenador instanceof Coordenador)) {
            throw new IllegalArgumentException("O usuário informado não é um coordenador.");
        }
        coordenadores.put(nome, (Coordenador) coordenador);
    }

    public Map<String, Coordenador> getCoordenadores() {
        return coordenadores;
    }

    public void addAluno(Usuario aluno, String nome) {
        if (!(aluno instanceof Aluno)) {
            throw new IllegalArgumentException("O usuário informado não é um aluno.");
        }
        alunos.put(nome, (Aluno) aluno);
    }

    public Map<String, Aluno> getAlunos() {
        return alunos;
    }
}
