package br.com.estoque.model.dao;

import br.com.estoque.connection.ConnectionFactory;
import br.com.estoque.model.bean.Categoria;
import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class CategoriaDAO {
    
    public void create(Categoria c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO categorias (idCategoria, Descricao) VALUES (?,?)");
            stmt.setInt(1, c.getIdCategoria());
            stmt.setString(2, c.getDescricao());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar! - "+ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    } 
    
    public List<Categoria> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Categoria> ListaCategorias = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM categorias");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("idCategoria"));
                c.setDescricao(rs.getString("Descricao"));

                ListaCategorias.add(c);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        } 
        return ListaCategorias;
    }
    
    public int readForDesc(String descricao){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        
        try {
            stmt = con.prepareCall("SELECT * FROM categorias WHERE Descricao = ?");
            stmt.setString(1, descricao);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                id = rs.getInt("idCategoria");
            }
            
        } catch (SQLException e) {
            System.out.println("Erro ao retornar o id - " +e);
        }
        return id;
    }
    
    public void update(Categoria c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE categorias SET Descricao = ? WHERE idCategoria = ?");
            stmt.setInt(2, c.getIdCategoria());
            stmt.setString(1, c.getDescricao());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    } 
    
    public void delete(Categoria c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM categorias WHERE idCategoria = ?");
            stmt.setInt(1, c.getIdCategoria());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    } 
    
    public HashMap listCat(){
        
        Map<Integer,String> list = new HashMap<>();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM categorias");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                list.put(rs.getInt("idCategoria"), rs.getString("Descricao"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        } 
        
        return (HashMap) list;
    }
}
