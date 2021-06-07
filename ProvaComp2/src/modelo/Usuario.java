package modelo;

public class Usuario {
    private String nome;
    private long cpf;
    private String endereço;

    public Usuario(String nome, long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {this.nome = nome;}

    public String getEndereco() {
        return endereço;
    }

    public void setEndereco(String endereco) {this.endereço = endereco;}

    @Override
    public int hashCode() {
        return Long.hashCode(cpf);
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode()? true: false;
    }
}
