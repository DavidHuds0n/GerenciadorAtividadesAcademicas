package negocios;

import java.util.ArrayList;
import dados.Disciplina;

public class Coordenador extends Usuario {
    private ArrayList<Disciplina> disciplinas = new ArrayList<>();

    public Coordenador(String username, String password) {
        super(username, password);
    }

    // Método para criar uma disciplina e atribuir um professor responsável
    public Disciplina criarDisciplina(String nome, String codigo, Professor professorResponsavel) {
        try {
            if (nome == null || nome.isEmpty() || codigo == null || codigo.isEmpty() || professorResponsavel == null) {
                throw new IllegalArgumentException("Nome, código ou professor responsável inválido(s).");
            }

            // Verifica se o nome ou código da disciplina já existe
            for (Disciplina disciplinaExistente : disciplinas) {
                if (disciplinaExistente.getNome().equals(nome) || disciplinaExistente.getCodigo().equals(codigo)) {
                    throw new IllegalStateException("Já existe uma disciplina com o mesmo nome ou código.");
                }
            }

            Disciplina disciplina = new Disciplina(nome, codigo, professorResponsavel);
            professorResponsavel.adicionarDisciplinaMinistrada(disciplina);
            this.disciplinas.add(disciplina);
            System.out.println("Disciplina criada com sucesso!");
            return disciplina;
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro ao criar disciplina: " + e.getMessage());
            return null;
        }
    }

    // Método para cadastrar um aluno em uma disciplina
    public void cadastrarAlunoDisciplina(Disciplina disciplina, Aluno aluno) {
        try {
            if (disciplina == null || aluno == null) {
                throw new IllegalArgumentException("Disciplina ou aluno inválido(s).");
            }

            // Verifica se o aluno já está matriculado na disciplina
            if (aluno.getDisciplinasMatriculadas().contains(disciplina)) {
                throw new IllegalStateException("O aluno já está matriculado nesta disciplina.");
            }

            disciplina.adicionarAluno(aluno);
            aluno.adicionarDisciplinaMatriculada(disciplina);
            System.out.println("Aluno matriculado com sucesso!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erro ao cadastrar aluno em disciplina: " + e.getMessage());
        }
    }

    // Método para retornar todas as disciplinas criadas pelo coordenador:
    public ArrayList<Disciplina> getDisciplinas(){
        return this.disciplinas;
    }
}