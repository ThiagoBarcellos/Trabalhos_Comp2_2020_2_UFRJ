import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite {

    public static int TAMANHO_MAXIMO_TUITES = 120;

    private List<Usuario> userList = new ArrayList<>();
    private HashMap<String, Integer> quantidadeByHashtag = new HashMap<>();

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {  // throws UsuarioJaExisteException {
        Usuario user = new Usuario(nome, email);
        userList.add(user);
        return user;
    }

    /**
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if(userList.contains(usuario)){
            if(texto.length() > TAMANHO_MAXIMO_TUITES){
                System.out.println("Numero máximo de caracteres excedido!!");
                return null;
            }

            Tuite tuite = new Tuite(usuario, texto, getHashtagsTuite(texto));

            return tuite;
        }
        return null;
    }

    public Set getHashtagsTuite(String texto){
        Set<String> hashTags = new HashSet<>();
        String[] particoesTexto = texto.split("\\s+");
        for (int i = 0; i < particoesTexto.length; i++){
            System.out.println(particoesTexto[i]);
            char c = particoesTexto[i].charAt(0);
            if(c == '#'){
                if(quantidadeByHashtag.get(particoesTexto[i]) == null){
                    hashTags.add(particoesTexto[i]);
                    quantidadeByHashtag.put(particoesTexto[i], 1);
                }
                else{
                    quantidadeByHashtag.put(particoesTexto[i], quantidadeByHashtag.get(particoesTexto[i])+1);
                }
            }
        }

        return hashTags;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        return Collections.max(quantidadeByHashtag.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
    }
}
