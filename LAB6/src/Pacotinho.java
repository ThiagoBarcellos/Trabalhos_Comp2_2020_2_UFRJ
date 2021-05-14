import java.util.Random;

public class Pacotinho {

    Repositorio repo;
    Figurinha[] figurinhas;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
        this.repo = repo;
        figurinhas = new Figurinha[posicoesDesejadas.length];
        for (int i = 0; i < posicoesDesejadas.length; i++){
            figurinhas[i] = new Figurinha(posicoesDesejadas[i], null);
        }
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas) {
        this.repo = repo;
        figurinhas = new Figurinha[quantFigurinhas];
        for (int i = 0; i < quantFigurinhas; i++){
            Random numeros = new Random();
            figurinhas[i] = new Figurinha(numeros.nextInt(repo.getTotalFigurinhas()), null);
        }
    }

    public Figurinha[] getFigurinhas() {
        return figurinhas;
    }
}