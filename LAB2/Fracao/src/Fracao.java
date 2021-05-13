/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */
public class Fracao {

    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */

    private int numerador;
    private int denominador;

    public Fracao(int numerador, int denominador) {
        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!!");
        }
        this.numerador=numerador;
        this.denominador=denominador;
    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal() { //ok
        if (numerador >= 0 && denominador > 0) return true;
        else if (numerador == 0) return true;
        else if (numerador < 0 && denominador < 0) return true;
        else return false;
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador() { return Math.abs(numerador); } //ok

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o numerador
     */
    public int getDenominador() { return Math.abs(denominador); } //ok

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar 0.33333333
     */
    public float getValorNumerico() {
        float novoNumerador = getNumerador();
        float novoDenominador = getDenominador();
        float sinal = getSinal() ? 1 : -1;
        float decimal = (novoNumerador/novoDenominador) * sinal;
        return decimal;
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */
    public Fracao getFracaoGeratriz() { //ok
        int novoNumerador = numerador;
        int novoDenominador = denominador;
        int sinal = getSinal() ? 1 : -1;
        int contadorSimplificacoes = 0;
        int mdc = AritmeticaUtils.mdc(novoNumerador, novoDenominador);
        while (mdc > 1){
            novoNumerador = novoNumerador/mdc;
            novoDenominador = novoDenominador/mdc;
            contadorSimplificacoes++;
            mdc = AritmeticaUtils.mdc(novoNumerador, novoDenominador);
        }
        if (contadorSimplificacoes>0) return new Fracao(novoNumerador * sinal, novoDenominador);
        else return this;
    }

    @Override
    public String toString() {  //ok
        String sinal = getSinal() ? "" : "-";
        String escritaFracao;
        if (getNumerador()!=0 && getDenominador() != 1) escritaFracao = sinal + getNumerador() + "/" + getDenominador();
        else escritaFracao = sinal + getNumerador();
        return escritaFracao;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == null||obj == null) return false;
        else if(((Fracao) obj).getValorNumerico() == this.getValorNumerico()) return true;
        else return false;
    }
}