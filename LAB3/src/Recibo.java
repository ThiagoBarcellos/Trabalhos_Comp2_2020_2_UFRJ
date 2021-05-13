import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Recibo {

    private Produto product;
    private int productQuant;
    private Usuario user;

    public Recibo(Produto produto, int quantidadeDesejada, Usuario usuario) {
        product = produto;
        productQuant = quantidadeDesejada;
        user = usuario;
    }

    private static DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
    private static DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
    private static char sep = symbols.getDecimalSeparator();

    public float getValorTotalDaCompra() {
        float totalValue = product.precoEmReais() * productQuant;
        return totalValue;
    }

    public Usuario getUsuario() {
        return user;
    }

    @Override
    public String toString() {
        String result;
        String productClass = product.getClass().toString();
        String[] parts = productClass.split(" ");
        productClass = parts[1];
        if (productQuant > 1){
            result = "Recibo no valor de R$" + (int)getValorTotalDaCompra() + sep + "00 para " + user.getUserName()
                + " referente à compra de " + productQuant + " unidades de " + productClass+ ": " + product.getDescricao();
        }
        else{
            result = "Recibo no valor de R$" + (int)getValorTotalDaCompra() + sep + "00 para " + user.getUserName()
                    + " referente à compra de " + productQuant + " unidade de " + product.getClass()+ ": " + product.getDescricao();
        }
        return result;
    }
}