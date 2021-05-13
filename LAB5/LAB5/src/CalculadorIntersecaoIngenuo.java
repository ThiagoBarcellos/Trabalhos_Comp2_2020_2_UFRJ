import java.util.ArrayList;
import java.util.List;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        List<Usuario> intersecao = new ArrayList<>();

        // para cada elemento da primeira lista, percorra a segunda lista até encontrá-lo (ou não)
        for(Usuario user1 : lista1){
            Usuario user = user1;
            for (Usuario user2 : lista2){
                if(user.compareTo(user2) == 0){
                    intersecao.add(user);
                    break;
                }
                if (user2.getId() > user.getId()){
                    break;
                }
            }
        }
        return intersecao;
    }
}
