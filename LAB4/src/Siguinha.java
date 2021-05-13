import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Siguinha {

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;

    private static Periodo periodoCorrente = null;

    String instituicaoDeEnsino;

    static Map<Long, Aluno> alunosBydre = new HashMap<>();

    public static int obterAnoCorrente() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private static int obterSemestreCorrente() {
        return obterMesCorrente() <= 6 ? 1 : 2;
    }

    public static int obterMesCorrente() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static Periodo obterPeriodoCorrente() {

        if (periodoCorrente != null) {
            if (periodoCorrente.getAno() != obterAnoCorrente() ||
                    periodoCorrente.getSemestre() != obterSemestreCorrente()) {
                periodoCorrente = null;  // invalida o cache
            }
        }

        if (periodoCorrente == null) {  // verifica o memo ("cache")
            // atualiza o cache
            periodoCorrente = new Periodo(obterAnoCorrente(), obterSemestreCorrente());
        }

        return periodoCorrente;
    }

    public static void cadastrarAluno(long dre, String nome){
        Aluno fulano = new Aluno(dre,nome);
        alunosBydre.put(dre, fulano);
    }

    static Aluno obterAluno(long dre){
        return alunosBydre.get(dre);
    }

    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) {
        cadastrarAluno(1234,"Fulano");
        System.out.println(obterAluno(1234).getNome());
        System.out.println(obterAluno(1234).getAnoNascimento());

        Disciplina calc1 = new Disciplina("Calculo1", 5, "12345");
        Periodo periodoCorrente = new Periodo(2020, 1);
        Professor prof = new Professor("prof1", 1975, 1993);
        Aluno aluno1 = new Aluno(123, "Jsp");
        Aluno aluno2 = new Aluno(1234, "Bia");

        Turma calculo1 = new Turma(calc1, periodoCorrente, prof);
        calculo1.inscreverAluno(aluno1);
        calculo1.inscreverAluno(aluno2);

        calculo1.atribuirMediaFinal(123, 3);
        calculo1.atribuirMediaFinal(1234, 3.5f);

        System.out.println(calculo1.obterMediaFinal(123));
        System.out.println(calculo1.obterMediaFinal(1234));

        System.out.println(calculo1.listarAlunos());

//        HashMap<Integer, String> numerosPorExtenso = new HashMap<>();
//        numerosPorExtenso.put(1, "um");
//        numerosPorExtenso.put(2, "dois");
//        numerosPorExtenso.put(3, "três");
//        numerosPorExtenso.put(4, "quatro");
//
//        System.out.println(numerosPorExtenso.get(2));
//        System.out.println(numerosPorExtenso.get(600));
//
//        // formar de iterar pelo mapa
//        for (Integer chave : numerosPorExtenso.keySet()) {
//            System.out.println(chave);
//        }
//
//        // formar de iterar pelo mapa
//        for (String valor : numerosPorExtenso.values()) {
//            System.out.println(valor);
//        }
//
//        for (Map.Entry<Integer, String> parChaveValor : numerosPorExtenso.entrySet()) {
//            Integer chave = parChaveValor.getKey();
//            String valor = parChaveValor.getValue();
//            System.out.println(chave + " ---> " + valor);
//        }

    }
}
