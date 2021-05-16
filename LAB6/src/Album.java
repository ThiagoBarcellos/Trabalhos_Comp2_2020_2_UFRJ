import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<Colecionavel> figurinhasColadas;  // direct addressing
    private int quantFigurinhasColadas;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.figurinhasColadas = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.figurinhasColadas.add(null);
        }
        this.quantFigurinhasColadas = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        Figurinha[] figurinhasDoPacotinho = pacotinho.getFigurinhas();
        if (figurinhasDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        for (Figurinha fig : pacotinho.getFigurinhas()) {
            final int posicao = fig.getPosicao();
            if (possuiItemColado(posicao)) {
                // adiciona como repetida

                // jeito pior
//                Integer contRepetidas = this.contRepetidasByPosicao.get(posicao);
//                this.contRepetidasByPosicao.put(
//                        posicao, contRepetidas == null ? 1 : contRepetidas + 1);

                // jeito mais elegante: getOrDefault
                int contRepetidas = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
                this.contRepetidasByPosicao.put(posicao, contRepetidas + 1);

            } else {
                // item inédito
                this.figurinhasColadas.set(posicao, fig);
                this.quantFigurinhasColadas++;
            }
        }
    }

    public Colecionavel getItemColado(int posicao) {
        return figurinhasColadas.get(posicao);
    }

    public boolean possuiItemColado(int posicao) {
        return posicao > 0 && posicao < figurinhasColadas.size() && figurinhasColadas.get(posicao) != null ? true : false;
    }

    public boolean possuiItemRepetido(int posicao) {
        if(possuiItemColado(posicao) && contRepetidasByPosicao.get(posicao)!= null){
            return true;
        }
        return false;
    }

    public int getTamanho() {
        return this.repositorio.getTotalFigurinhas();
    }

    public int getQuantItensColados() {
//        int contador = 0;
//        for (Figurinha fig : this.figurinhasColadas) {
//            if (fig != null) {
//                contador++;
//            }
//        }
//        return contador;

        // melhor jeito: atributo!
        return this.quantFigurinhasColadas;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        if(getQuantItensFaltantes() == getTamanho() || getQuantItensColados() < (int)(PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR
                * repositorio.getTotalFigurinhas())/100f){
            return;
        }

        for (int i = 1; i < figurinhasColadas.size(); i++){
            if(figurinhasColadas.get(i) == null){
                Figurinha fig = new Figurinha(i, null);
                this.figurinhasColadas.set(i, fig);
                this.quantFigurinhasColadas++;
            }
        }
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }

    public static void main(String[] args) {
        List<Colecionavel> figurinhasColadas = new ArrayList<>(2);
        Selo selo = new Selo(0, 0.15f,"Brazil");
        Figurinha fig = new Figurinha(1, null);
        figurinhasColadas.add(selo);
        figurinhasColadas.add(fig);

        for(Colecionavel col : figurinhasColadas){
            System.out.println(col);
        }
    }
}