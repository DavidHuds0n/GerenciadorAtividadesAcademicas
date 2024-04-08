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
        String username = usuario.getUsername();
        usuarios.put(username, usuario);
    }

    public Usuario buscarUsuario(String username) {
        return usuarios.get(username);
    }
    
    public boolean autenticarUsuario(String username, String password) {
        Usuario usuario = buscarUsuario(username);
        return usuario != null && usuario.getPassword().equals(password);
    }

    public void addProfessor(Usuario professor, String nome){
        Professor novo = (Professor) professor;
        this.professores.put(nome, novo);
    }

    public Map<String, Professor> getProfessores(){
        return this.professores;
    }

    public void addCoordenador(Usuario coordenador, String nome){
        Coordenador novo = (Coordenador) coordenador;
        this.coordenadores.put(nome, novo);
    }

    public Map<String, Coordenador> getCoordenadores(){
        return this.coordenadores;
    }

    public void addAluno(Usuario aluno, String nome){
        Aluno novo = (Aluno) aluno;
        this.alunos.put(nome, novo);
    }

    public Map<String, Aluno> getAlunos(){
        return this.alunos;
    }
}
