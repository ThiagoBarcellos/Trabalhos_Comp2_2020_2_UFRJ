public class Produto {
    private String description;
    private String urlImage;
    private float price;
    private int weight;

    public Produto(String descricao, String urlDaImagem) {
        description = descricao;
        urlImage = urlDaImagem;
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() { return this.description; }

    public int getPesoEmGramas() {
        return weight;
    }

    public Dimensoes getDimensoes() {
        return null;
    }

    public float precoEmReais() { return price; }

    public void setPrecoEmReais(float preco) {
        price = preco;
    }

    public String getUrlDaImagem() {
        return this.urlImage;
    }
}