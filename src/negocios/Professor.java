package negocios;

import java.time.LocalDate;
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
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina inválida.");
        }
        disciplinasMinistradas.add(disciplina);
    }
    
    // Método para acessar todas as disciplinas ministradas pelo professor
    public List<Disciplina> getDisciplinasMinistradas() {
        return disciplinasMinistradas;
    }

    // Método para inserir atividade em uma disciplina
    public void criarAtividade(Disciplina disciplina, String atividade, LocalDate data) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina inválida.");
        }
        if (atividade == null || atividade.isEmpty()) {
            throw new IllegalArgumentException("Atividade inválida.");
        }
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida.");
        }
        disciplina.inserirAtividade(atividade, data);
    }

    // Método para definir data da prova em uma disciplina
    public void definirDataProva(Disciplina disciplina, LocalDate data) {
        if (disciplina == null) {
            throw new IllegalArgumentException("Disciplina inválida.");
        }
        if (data == null || data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data inválida.");
        }
        disciplina.inserirDataProva(data);
    }

    // Método para inserir notas em uma disciplina
    public void inserirNota(Disciplina disciplina, Aluno aluno, Double nota) {
        if (disciplina == null || aluno == null || nota == null) {
            throw new IllegalArgumentException("Parâmetros inválidos.");
        }
        disciplina.inserirNota(aluno, nota);
    }
}
