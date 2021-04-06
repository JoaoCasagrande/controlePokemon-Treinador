package conexao;

//Autor: Daniel Vendramim Soares

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
    
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetojava?serverTimezone=UTC","root","");//Linha de conexão, Usuário do MySQL e Senha do MySQL
            return conn;
        } catch(Exception ex){
            System.out.println("Erro ao se conectar: " + ex.getMessage());
            return null;
        }
    }
    
}
