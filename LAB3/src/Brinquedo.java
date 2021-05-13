public class Brinquedo extends Produto {

    private String description;
    private String brand;
    private int minimumAge;

    public Brinquedo(String descricao) {
        super(descricao, null);
    }

    public String getMarca() {
        return brand;
    }

    public int getIdadeMinimaRecomendada() {
        return minimumAge;
    }
}
