import java.util.HashMap;
import java.util.Map;

public class Turma {
    private Disciplina disciplina;
    private Periodo periodo;
    private Professor professorResponsavel;

    Map<Long, Aluno> alunosInscritosBydre = new HashMap<>();
    Map<Long, Float> notaFinalBydre = new HashMap<>();

    public Turma(Disciplina disciplina, Periodo periodo, Professor professorResponsavel){
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.professorResponsavel = professorResponsavel;
    }

    public void inscreverAluno(Aluno aluno){
        long dre = aluno.getDre();
        alunosInscritosBydre.put(dre, aluno);
    }

    public void atribuirMediaFinal(long dre, float nota){
        notaFinalBydre.put(dre,nota);
    }

    public float obterMediaFinal(long dre){
        return notaFinalBydre.get(dre);
    }

    public String listarAlunos(){
        String listaAlunos = "";
        for (Aluno aluno : alunosInscritosBydre.values()) {
            listaAlunos += aluno.getNome();
            listaAlunos += "\n";
        }
        return listaAlunos;
    }
}
