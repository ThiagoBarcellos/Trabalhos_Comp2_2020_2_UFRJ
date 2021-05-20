import java.awt.*;

public class Selo implements Colecionavel{
    private int posicao;
    private float valorNominal;
    private String paisOrigem;
    private String urlDaImagem;

    public Selo(int posicao, String urlDaImagem){
        this.posicao = posicao;
        this.urlDaImagem = urlDaImagem;
    }

    public float getValorNominal() {
        return valorNominal;
    }

    public String getPais() {
        return paisOrigem;
    }

    @Override
    public Image getImagem() {
        return null;
    }
    @Override
    public int getPosicao() {
        return posicao;
    }
}
