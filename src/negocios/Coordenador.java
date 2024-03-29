package negocios;

import dados.Disciplina;

public class Coordenador extends Usuario {
    // Outros atributos, se houver

    public Coordenador(String username, String password) {
        super(username, password);
    }

    // Método para criar uma disciplina e atribuir um professor responsável
    public Disciplina criarDisciplina(String nome, String codigo, Professor professorResponsavel) {
        Disciplina disciplina = new Disciplina(nome, codigo, professorResponsavel);
        professorResponsavel.adicionarDisciplinaMinistrada(disciplina);
        return disciplina;
    }

    // Método para cadastrar um aluno em uma disciplina
    public void cadastrarAlunoDisciplina(Disciplina disciplina, Aluno aluno) {
        disciplina.adicionarAluno(aluno);
        aluno.adicionarDisciplinaMatriculada(disciplina);
    }
}