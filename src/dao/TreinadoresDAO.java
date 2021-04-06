package dao;
import beans.Treinadores;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TreinadoresDAO {
    private Conexao conexao;
    private Connection conn;
       
    public TreinadoresDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void inserir(Treinadores treinador){
        String sql = "INSERT INTO treinadores(nome, sexo) VALUES (?, ?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, treinador.getNome());
            stmt.setString(2, treinador.getSexo());
            stmt.execute();
        } catch(Exception ex){
            System.out.println("Erro ao inserir treinador: " + ex.getMessage());
        }
    }
    
    public Treinadores getTreinador(int id){
        String sql = "SELECT * FROM treinadores WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Treinadores treinador = new Treinadores();
            //Set ResultSet na primeira posição
            rs.first();
            treinador.setId(id);
            treinador.setNome(rs.getString("nome"));
            treinador.setSexo(rs.getString("sexo"));
            return treinador;
        } catch(Exception ex){
            ex.getMessage();
            return null;
        }
    }
    
    public void update(Treinadores treinador){
        String sql = "UPDATE treinadores SET nome = ?, sexo = ? WHERE id = ?;";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, treinador.getNome());
            stmt.setString(2, treinador.getSexo());
            stmt.setInt(3, treinador.getId());
            stmt.execute();
        } catch(Exception ex){
            System.out.println("Erro ao editar treinador: " + ex.getMessage());
        }
    }
    
    public void delete(int id){
        String sql = "DELETE FROM treinadores WHERE id = ?;";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch(Exception ex){
            System.out.println("Erro ao excluir treinador: " + ex.getMessage());
        }
    }
    
    public List<Treinadores> getTreinadores(String nome){
        String sql = "SELECT * FROM treinadores WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Treinadores> listaTreinadores = new ArrayList<>();
            while(rs.next()){
                Treinadores treinador = new Treinadores();
                treinador.setId(rs.getInt("id"));
                treinador.setNome(rs.getString("nome"));
                treinador.setSexo(rs.getString("sexo"));
                listaTreinadores.add(treinador);
            }
            return listaTreinadores;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
