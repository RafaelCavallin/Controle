package br.com.estoque.model.dao;

import br.com.estoque.connection.ConnectionFactory;
import br.com.estoque.model.bean.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


public class FornecedorDAO {
    
    public void create(Fornecedor f){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO fornecedores (idFornecedor, RazaSocial, CNPJ, IE, Endereco, Bairro, Cidade, UF, FoneFixo, FoneCelular, Email, Responsavel) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, f.getIdFornecedor());
            stmt.setString(2, f.getRazaoSocial());
            stmt.setString(3, f.getCNPJ());
            stmt.setString(4, f.getIE());
            stmt.setString(5, f.getEnd());
            stmt.setString(6, f.getBairro());
            stmt.setString(7, f.getCidade());
            stmt.setString(8, f.getUF());
            stmt.setString(9, f.getFoneFixo());
            stmt.setString(10, f.getFoneCelular());
            stmt.setString(11, f.getEmail());
            stmt.setString(12, f.getResp());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar - " +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    
    public List<Fornecedor> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> Fornecedores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedores");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Fornecedor f = new Fornecedor();
                f.setIdFornecedor(rs.getInt("idFornecedor"));
                f.setRazaoSocial(rs.getString("RazaSocial"));
                f.setCNPJ(rs.getString("CNPJ"));
                f.setIE(rs.getString("IE"));
                f.setEnd(rs.getString("Endereco"));
                f.setBairro(rs.getString("Bairro"));
                f.setCidade(rs.getString("Cidade"));
                f.setUF(rs.getString("UF"));
                f.setFoneFixo(rs.getString("FoneFixo"));
                f.setFoneCelular(rs.getString("FoneCelular"));
                f.setEmail(rs.getString("Email"));
                f.setResp(rs.getString("Responsavel"));
                                
                Fornecedores.add(f);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar! - " +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        } 
        return Fornecedores;
    }
    
    public int readIdForDesc(String descricao){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        
        try {
             stmt = con.prepareStatement("SELECT * FROM fornecedores WHERE razaSocial = ?");
             stmt.setString(1, descricao);
             rs = stmt.executeQuery();
             
             while (rs.next()) {                
                id = rs.getInt("idFornecedor");
            }
             
        } catch (SQLException e) {
            System.out.println("Erro ao retornar ID do fornecedor - " +e);
        }
        return id;
    }
    
    public void update(Fornecedor f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE fornecedores SET RazaSocial = ?, CNPJ = ?, IE = ?, Endereco = ?, Bairro = ?, Cidade = ?, UF = ?, FoneFixo = ?, FoneCelular = ?, Email = ?, Responsavel = ?  WHERE idFornecedor = ?");
            stmt.setInt(12, f.getIdFornecedor());
            stmt.setString(1, f.getRazaoSocial());
            stmt.setString(2, f.getCNPJ());
            stmt.setString(3, f.getIE());
            stmt.setString(4, f.getEnd());
            stmt.setString(5, f.getBairro());
            stmt.setString(6, f.getCidade());
            stmt.setString(7, f.getUF());
            stmt.setString(8, f.getFoneFixo());
            stmt.setString(9, f.getFoneCelular());
            stmt.setString(10, f.getEmail());
            stmt.setString(11, f.getResp());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar! - " +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    } 
    
    public void delete(Fornecedor f){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM fornecedores WHERE idfornecedor = ?");
            stmt.setInt(1, f.getIdFornecedor());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }  
    
    public List<Fornecedor> readForDesc(String busca){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fornecedor> Fornecedores = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedores WHERE RazaSocial LIKE ?");
            stmt.setString(1, "%"+ busca +"%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Fornecedor f = new Fornecedor();
                f.setIdFornecedor(rs.getInt("idFornecedor"));
                f.setRazaoSocial(rs.getString("RazaSocial"));
                f.setCNPJ(rs.getString("CNPJ"));
                f.setIE(rs.getString("IE"));
                f.setEnd(rs.getString("Endereco"));
                f.setBairro(rs.getString("Bairro"));
                f.setCidade(rs.getString("Cidade"));
                f.setUF(rs.getString("UF"));
                f.setFoneFixo(rs.getString("FoneFixo"));
                f.setFoneCelular(rs.getString("FoneCelular"));
                f.setEmail(rs.getString("Email"));
                f.setResp(rs.getString("Responsavel"));
                                
                Fornecedores.add(f);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar! - " +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        } 
        return Fornecedores;
    }
    
    public HashMap listFor(){
        
        Map<String,String> list = new HashMap<>();
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM fornecedores");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                list.put(Integer.toString(rs.getInt("idFornecedor")), rs.getString("RazaSocial"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        } 
        return (HashMap) list;
    }
}