import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsuarioTeste {

    private CalculadorIntersecao calculadorIntersecao;
    private Usuario user1;
    private Usuario user2;
    private int quantidade;
    private final int QuantidadeAmigos = 10000;
    private final int expected = 3334;

    @Before
    public void setUp() {
    }

    @Test
    public void testarIntersecaoIngenua(){
        calculadorIntersecao = new CalculadorIntersecaoIngenuo();
        user1 = new Usuario(1, calculadorIntersecao);
        user2 = new Usuario(2, calculadorIntersecao);

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*2, calculadorIntersecao);
            user1.adicionarAmigos(userAdicionar);
        }

        for(int i = 0; i < QuantidadeAmigos + 7; i++){
            Usuario userAdicionar = new Usuario(i*3, calculadorIntersecao);
            user2.adicionarAmigos(userAdicionar);
        }

        quantidade = user1.obterQuantidadeDeAmigosEmComum(user2);

        assertEquals("A quantidade de amigos em comum cadastrada deve ser igual", expected, quantidade);
    }

    @Test
    public void testarIntersecaoEsperto(){
        calculadorIntersecao = new CalculadorIntersecaoEsperto();
        user1 = new Usuario(1, calculadorIntersecao);
        user2 = new Usuario(2, calculadorIntersecao);

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*2, calculadorIntersecao);
            user1.adicionarAmigos(userAdicionar);
        }

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*3, calculadorIntersecao);
            user2.adicionarAmigos(userAdicionar);
        }

        quantidade = user1.obterQuantidadeDeAmigosEmComum(user2);

        assertEquals("A quantidade de amigos em comum cadastrada deve ser igual", expected, quantidade);
    }

    @Test
    public void testarIntersecaoViaBuscaBinaria(){
        calculadorIntersecao = new CalculadorIntersecaoViaBuscaBinaria();
        user1 = new Usuario(1, calculadorIntersecao);
        user2 = new Usuario(2, calculadorIntersecao);

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*2, calculadorIntersecao);
            user1.adicionarAmigos(userAdicionar);
        }

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*3, calculadorIntersecao);
            user2.adicionarAmigos(userAdicionar);
        }
        quantidade = user1.obterQuantidadeDeAmigosEmComum(user2);

        assertEquals("A quantidade de amigos em comum cadastrada deve ser igual", expected, quantidade);
    }

    @Test
    public void testarPerformance(){
        //Ingenuo-----------------------------------
        calculadorIntersecao = new CalculadorIntersecaoIngenuo();
        user1 = new Usuario(1, calculadorIntersecao);
        user2 = new Usuario(2, calculadorIntersecao);

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*2, calculadorIntersecao);
            user1.adicionarAmigos(userAdicionar);
        }

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*3, calculadorIntersecao);
            user2.adicionarAmigos(userAdicionar);
        }

        double start = System.nanoTime();

        quantidade = user1.obterQuantidadeDeAmigosEmComum(user2);

        double elapsed = (System.nanoTime() - start)/1000000000;
        System.out.printf("o metodo ingenuo executou em %.4f\n", elapsed);

        //Esperto---------------------------------------------
        calculadorIntersecao = new CalculadorIntersecaoEsperto();
        user1 = new Usuario(1, calculadorIntersecao);
        user2 = new Usuario(2, calculadorIntersecao);

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*2, calculadorIntersecao);
            user1.adicionarAmigos(userAdicionar);
        }

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*3, calculadorIntersecao);
            user2.adicionarAmigos(userAdicionar);
        }

        start = System.nanoTime();
        quantidade = user1.obterQuantidadeDeAmigosEmComum(user2);

        elapsed = (System.nanoTime() - start)/1000000000;
        System.out.printf("o metodo esperto executou em %.4f\n", elapsed);

        //Binario-------------------------------------
        calculadorIntersecao = new CalculadorIntersecaoViaBuscaBinaria();
        user1 = new Usuario(1, calculadorIntersecao);
        user2 = new Usuario(2, calculadorIntersecao);

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*2, calculadorIntersecao);
            user1.adicionarAmigos(userAdicionar);
        }

        for(int i = 0; i < QuantidadeAmigos; i++){
            Usuario userAdicionar = new Usuario(i*3, calculadorIntersecao);
            user2.adicionarAmigos(userAdicionar);
        }

        start = System.nanoTime();
        quantidade = user1.obterQuantidadeDeAmigosEmComum(user2);

        elapsed = (System.nanoTime() - start)/1000000000;
        System.out.printf("o metodo binario executou em %.4f\n",elapsed);
    }
}
