package beans;

public class Treinadores {
    private int id;
    private String nome, sexo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    //Retornar a representação do objeto em String
    public String toString(){
        return this.getNome();
    }
    
    //Comparar a String
    public boolean equals(Object objeto){
        Treinadores treinador = (Treinadores) objeto;
        return this.id == treinador.getId();
    }
}
