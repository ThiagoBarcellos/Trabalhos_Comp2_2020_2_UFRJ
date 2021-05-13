import java.util.*;

public class EncontrarPar {

    static void encontrarPar(List<Integer> lista, int k){
        HashSet<Integer> numbers = new HashSet<>();

        List<String> somas = new ArrayList<>();

        for(int i : lista){
            numbers.add(i);
        }

        int j = 0;
        for (int i : numbers){
            if(numbers.contains(k-i) && i != k-i){
                j++;
                StringBuffer sB = new StringBuffer();
                if(i>0 && k-i>0 || k-i>0) sB.append("Soma ").append(j).append(": ").append(i).append("+").append(k-i);
                else if(i>0 && k-i<0)sB.append("Soma ").append(j).append(": ").append(i).append(k-i);
                somas.add(sB.toString());
            }
        }

        System.out.println("Lista:");
        for (int i : numbers){
            System.out.printf(i + ", ");
        }
        System.out.printf("\nTodas as somas possiveis da lista para %d são:\n", k);
        for (String s : somas){
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(5);
        ints.add(-10);
        ints.add(56);
        ints.add(44);
        ints.add(12);
        ints.add(18);
        encontrarPar(ints, 34);
    }
}
