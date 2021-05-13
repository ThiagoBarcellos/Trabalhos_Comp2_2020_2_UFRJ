public class Livro extends Produto {

    private int pages;
    private String featuredExcerpt;
    private String author;
    private int publicationYear;

    public Livro(String nome, String editora) {
        super(nome,null);

    }

    private int numeroDePaginas() { return pages; }

    public String getTrechoEmDestaque() {
        return featuredExcerpt;
    }

    public String getAutor() {
        return author;
    }

    public int getAnoDePublicacao() {
        return publicationYear;
    }
}
