import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends Colecionavel>{

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";
    private final T objetoReferencia;

    private List<T> todasAsFigurinhas;

    public Repositorio(String sufixoUrlImagens, int quantFigurinhas, T objetoReferencia) {
        this.objetoReferencia = objetoReferencia;
        todasAsFigurinhas = new ArrayList<>(quantFigurinhas);
        for (int i = 1; i <= quantFigurinhas; i++) {
            T fig = (T) ColecionavelFactory.create(
                    objetoReferencia.getClass().getName(), i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todasAsFigurinhas.add(fig);
        }
    }

    public String getClasse(){
        return objetoReferencia.getClass().toString().intern();
    }

    public int getTotalFigurinhas() {
        return this.todasAsFigurinhas.size();
    }
}