import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pacotinho<T extends Colecionavel> {

    Repositorio repo;
    List<T> colecionavel;
    private final T objetoReferencia;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas, T objetoReferencia) {
        this.repo = repo;
        this.objetoReferencia = objetoReferencia;
        colecionavel = new ArrayList<>(posicoesDesejadas.length);
        if(getClasse().equals(repo.getClasse())){
            for (int i = 0; i < posicoesDesejadas.length; i++){
                colecionavel.add((T) ColecionavelFactory.create(
                        objetoReferencia.getClass().getName(), posicoesDesejadas[i], null));
            }
        }
        else{
            System.out.println("Pacotinho de tipo diferente do repositório!!!!");
        }
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas,T objetoReferencia) {
        this.repo = repo;
        this.objetoReferencia = objetoReferencia;
        colecionavel = new ArrayList<>(quantFigurinhas);
        if(getClasse().equalsIgnoreCase(repo.getClasse())) {
            for (int i = 0; i < quantFigurinhas; i++) {
                Random numeros = new Random();
                int pos = numeros.nextInt(repo.getTotalFigurinhas()) + 1;
                colecionavel.add((T) ColecionavelFactory.create(
                        objetoReferencia.getClass().getName(), pos, null));
            }
        }
        else{
            System.out.println("Pacotinho de tipo diferente do repositório!!!!");
        }
    }

    public String getClasse(){
        return objetoReferencia.getClass().toString().intern();
    }

    public List<T> getColecionaveis() {
        return colecionavel;
    }
}