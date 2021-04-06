package dao;

import beans.Pokemon;
import beans.Treinadores;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAO {
    private Conexao conexao;
    private Connection conn;
    
    public PokemonDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void insert(Pokemon poke){
        String sql = "INSERT INTO pokemon(nome, sexo, treinadorid) VALUES (?, ?, ?);";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, poke.getNome());
            stmt.setString(2, poke.getSexo());
            stmt.setInt(3, poke.getTreinadorid().getId());
            stmt.execute();
        } catch(Exception ex){
            System.out.println("Erro ao inserir pokemon: " + ex.getMessage());
        }
    }
    
    public void update(Pokemon poke){
        String sql = "UPDATE pokemon SET nome = ?, sexo = ?, treinadorid = ? WHERE id = ?;";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, poke.getNome());
            stmt.setString(2, poke.getSexo());
            stmt.setInt(3, poke.getTreinadorid().getId());
            stmt.setInt(4, poke.getId());
            stmt.execute();
        } catch(Exception ex){
            System.out.println("Erro ao editar pokémon: " + ex.getMessage());
        }
    }
    
    public void delete(int id){
        String sql = "DELETE FROM pokemon WHERE id = ?;";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch(Exception ex){
            System.out.println("Erro ao excluir pokemon: " + ex.getMessage());
        }
    }
    
    public List<Pokemon> getPokemons(){
        String sql = "SELECT p.id, p.nome, p.sexo, treinadorid, t.id, t.nome FROM pokemon AS p INNER JOIN treinadores AS t ON (p.treinadorid = t.id)";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Pokemon> listaPokemons = new ArrayList<>();
            while(rs.next()){
                Pokemon poke = new Pokemon();
                Treinadores treinador = new Treinadores();
                poke.setId(rs.getInt("p.id"));
                poke.setNome(rs.getString("p.nome"));
                poke.setSexo(rs.getString("p.sexo"));
                treinador.setId(rs.getInt("t.id"));
                treinador.setNome(rs.getString("t.nome"));
                poke.setTreinadorid(treinador);
                listaPokemons.add(poke);
            }
            return listaPokemons;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Pokemon getPokemon(int id){
        String sql = "SELECT * FROM pokemon WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Pokemon poke = new Pokemon();
            //Set ResultSet na primeira posição
            rs.first();
            poke.setId(id);
            poke.setNome(rs.getString("nome"));
            poke.setSexo(rs.getString("sexo"));
            Treinadores treinadorid = new Treinadores();
            treinadorid.setId(rs.getInt("treinadorid"));
            poke.setTreinadorid(treinadorid);
            return poke;
        } catch(Exception ex){
            ex.getMessage();
            return null;
        }
    }
}
