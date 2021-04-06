package beans;

public class Pokemon {
    private int id;
    private String nome, sexo;
    private Treinadores treinadorid;

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

    public Treinadores getTreinadorid() {
        return treinadorid;
    }

    public void setTreinadorid(Treinadores treinadorid) {
        this.treinadorid = treinadorid;
    }
    
}
