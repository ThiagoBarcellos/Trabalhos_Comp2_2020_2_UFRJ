import java.lang.*;
public class Primos {

    public static boolean ehPrimo(int numero) {
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0)
                return false;
        }
        return true;
    }

    public static int[] obterPrimos(int n){
        int j=0;
        int[] numPrimos = new int[(n/2)+1];
        for (int i = 2; i<n; i++){
            if (ehPrimo(i)){
                numPrimos[j] = i;
                j++;
            }
        }
        int[] numRealPrimos = new int[j];
        for (int i = 0; i < j; i++){
            if (numPrimos[i] != 0)
                numRealPrimos[i] = numPrimos[i];
        }
        return numRealPrimos;
    }

    public static int[] obterPrimosViaCrivo(int n){
        /*false para primo true para composto*/
        int limite = (int) Math.sqrt(n);
        int divisor;
        int l = 0;
        boolean[] listaComposto = new boolean[n];
        for (int i = 2; i <= limite; i++) {
            listaComposto[0]=true;
            listaComposto[1]=true;
            divisor = i;
            for (int j = i + divisor; j < n; j += divisor) {
                listaComposto[j] = true;
            }
        }
        for (int k = 0; k < n; k++){
            if (listaComposto[k] == false)
                l++;
        }
        int[] listaPrimos = new int[l];
        l=0;
        for (int i = 0; i < n; i++){
            if(listaComposto[i] == false)
                listaPrimos[l] = i;
        }
        return listaPrimos;
    }

    public static void main(String[] args) {

        //Execução sem crivo
        double inicio = System.currentTimeMillis();
        for(int n=10; n<=10_000; n*=10){
            int[] primos = obterPrimos(n);
        }
        double fim  = System.currentTimeMillis();
        System.out.printf("tempo sem crivo: %.4f segundos\n", (fim - inicio)/1_000);

        //Execução com crivo
        inicio = System.currentTimeMillis();
        for(int n=10; n<=10_000; n*=10){
            int[] primos = obterPrimosViaCrivo(n);
        }
        fim  = System.currentTimeMillis();
        System.out.printf("tempo com crivo: %.4f segundos\n", (fim - inicio)/1_000);
    }
}
