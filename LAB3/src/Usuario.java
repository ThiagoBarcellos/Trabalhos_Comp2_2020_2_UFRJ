public class Usuario {

    private String userName;
    private long userCPF;
    private String userAddress;

    public Usuario(String nome, long cpf, String endereco) {
        userName = nome;
        userCPF = cpf;
        userAddress = endereco;
    }

    public String getUserName() {
        return userName;
    }

    public long getUserCPF() {
        return userCPF;
    }

    public String getUserAddress() {
        return userAddress;
    }
}
