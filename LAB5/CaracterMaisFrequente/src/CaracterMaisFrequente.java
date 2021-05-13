import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CaracterMaisFrequente {

    public static void caracterMaisFrequente(String string){
        HashMap<Character, Integer> mapaDeFrequencia = new HashMap<>();

        for (int i = 0; i < string.length(); i++){
            char caracter = string.charAt(i);
            if (mapaDeFrequencia.containsKey(caracter)) mapaDeFrequencia.put(caracter, mapaDeFrequencia.get(caracter)+1);
            else mapaDeFrequencia.put(caracter,1);
        }

        System.out.printf(Collections.max(mapaDeFrequencia.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey() +
                " = "
                + Collections.max(mapaDeFrequencia.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue());
    }

    public static void main(String[] args) {
        caracterMaisFrequente("hello world");
    }
}
