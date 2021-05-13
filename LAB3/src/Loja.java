import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private static final Loja instanciaUnica = new Loja();
    private ArrayList products = new ArrayList();
    private ArrayList productQuant = new ArrayList();
    private ArrayList<Usuario> user = new ArrayList<>();

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    private Loja() {
        // escrevo código normalmente para o construtor
    }

    public static Loja getInstanciaUnica() {
        return instanciaUnica;
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        if(!products.contains(produto)){
            products.add(produto);
            productQuant.add(quantidadeAIncluir);
        }
        else {
            int index = products.indexOf(produto);
            productQuant.set(index, ((int)productQuant.get(index)+quantidadeAIncluir));
        }
    }

    public void cadastrarUsuario(Usuario usuario) {
        if(!user.contains(usuario)){
            user.add(usuario);
        }
    }

    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(Produto produto, int quantidadeDesejada, Usuario usuario) {
        if (products.contains(produto) && user.contains(usuario)){
            int index = products.indexOf(produto);
            if((int)productQuant.get(index) > quantidadeDesejada)
            {
                Recibo recibo = new Recibo(produto, quantidadeDesejada, usuario);
                productQuant.set(index, ((int)productQuant.get(index)-quantidadeDesejada));

                return recibo;
            }
            else return null;
        }
        else{
            return null;
        }
    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
        int index = products.indexOf(produto);
        return (int)productQuant.get(index);
    }
}
