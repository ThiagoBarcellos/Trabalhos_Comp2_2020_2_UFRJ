import java.awt.*;

public class Selo implements Colecionavel{
    private int posicao;
    private float valorNominal;
    private String paisOrigem;

    public Selo(int posicao, float valorNominal, String paisOrigem){
        this.posicao = posicao;
        this.valorNominal = valorNominal;
        this.paisOrigem = paisOrigem;
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
