import java.awt.*;

public class Figurinha implements Colecionavel{

    private final Image imagem;
    private final int posicao;

    public Figurinha(int posicao, String urlDaImagem) {
        this.imagem = obterImagem(urlDaImagem);
        this.posicao = posicao;
    }

    private Image obterImagem(String url) {
        // ToDo baixaria da Internet...
        return null;
    }

    @Override
    public Image getImagem() {
        return imagem;
    }
    @Override
    public int getPosicao() {
        return posicao;
    }
}