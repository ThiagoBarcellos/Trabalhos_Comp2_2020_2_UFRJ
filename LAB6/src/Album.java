import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album <T extends Colecionavel> {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<T> colecionaveisColados;  // direct addressing
    private int quantColecionaveisColadas;

    private final T objetoReferencia;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho, T objetoReferencia) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;
        this.objetoReferencia = objetoReferencia;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.colecionaveisColados = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.colecionaveisColados.add(null);
        }
        this.quantColecionaveisColadas = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public String getClasse(){
        return objetoReferencia.getClass().toString().intern();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        List<T> figurinhasDoPacotinho = new ArrayList<>(pacotinho.getColecionaveis());
        if (figurinhasDoPacotinho.size() != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        if (getClasse().equalsIgnoreCase(pacotinho.getClasse())){
            for (T item : (List<T>)pacotinho.getColecionaveis()) {
                final int posicao = item.getPosicao();
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
                    this.colecionaveisColados.set(posicao, (T)item);
                    this.quantColecionaveisColadas++;
                }
            }
        }
        else{
            System.out.println("Pacotinho de tipo diferente do album!!");
        }
    }

    public T getItemColado(int posicao) {
        return colecionaveisColados.get(posicao);
    }

    public boolean possuiItemColado(int posicao) {
        return posicao > 0 && posicao < colecionaveisColados.size() && colecionaveisColados.get(posicao) != null ? true : false;
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
        return this.quantColecionaveisColadas;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        if(getQuantItensFaltantes() == getTamanho() || getQuantItensColados() < (int)(PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR
                * repositorio.getTotalFigurinhas())/100f){
            return;
        }

        for (int i = 1; i < colecionaveisColados.size(); i++){
            if(colecionaveisColados.get(i) == null){
                Figurinha fig = new Figurinha(i, null);
                this.colecionaveisColados.set(i, (T)fig);
                this.quantColecionaveisColadas++;
            }
        }
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }

    public static void main(String[] args) {
//        Repositorio repo = new Repositorio("", 200);
//        Album<Figurinha> album1 = new Album<Figurinha>(repo,3);
//        Pacotinho<Figurinha> pacoteFigurinha = new Pacotinho<>(repo, new int[]{3,4,5});
//        Pacotinho<Selo> pacoteSelo = new Pacotinho<>(repo, new int[]{0,1,2});
//        album1.receberNovoPacotinho(pacoteFigurinha);
//        album1.receberNovoPacotinho(pacoteSelo);
//
//        System.out.println(album1.getItemColado(2));
//        System.out.println(album1.getItemColado(5));
    }
}