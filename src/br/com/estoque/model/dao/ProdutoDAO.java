package br.com.estoque.model.dao;

import br.com.estoque.connection.ConnectionFactory;
import br.com.estoque.model.bean.Produto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    public void create(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (idUsuario, idCategoria, IdFornecedor, Descrcao, CadigoDeBarras, ValorCusto, ValorVenda, EstoqueMinimo, Quantidade, UnidMedida, Estado, DataCadastro, Imagem) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, p.getIdUsuario());
            stmt.setInt(2, p.getIdCategoria());
            stmt.setInt(3, p.getIdFornecedor());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getCodigoDeBarras());
            stmt.setDouble(6, p.getValorCusto());
            stmt.setDouble(7, p.getValorVenda());
            stmt.setInt(8, p.getEstMinimo());
            stmt.setInt(9, p.getQuantidade());
            stmt.setInt(10, p.getUnidadeDeMedida());
            stmt.setBoolean(11, p.getEstado());
            stmt.setDate(12, (Date) p.getDataCadastro());
            stmt.setString(13, p.getImagem());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" +ex);
        } finally{
            ConnectionFactory.CloseConnection(con, stmt);
        } 
    }
    
    public List<Produto> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> Produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setIdUsuario(rs.getInt("idUsuario"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdFornecedor(rs.getInt("idFornecedor"));
                p.setDescricao(rs.getString("Descricao"));
                p.setCodigoDeBarras(rs.getString("CodigoDeBarras"));
                p.setValorCusto(rs.getDouble("ValorCusto"));
                p.setValorVenda(rs.getDouble("ValorVenda"));
                p.setEstMinimo(rs.getInt("EstoqueMinimo"));
                p.setQuantidade(rs.getInt("Quantidade"));
                p.setUnidadeDeMedida(rs.getInt("UnidMedida"));
                p.setEstado(rs.getBoolean("Estado"));
                p.setDataCadastro(rs.getDate("DataCadastro"));
                p.setImagem(rs.getString("Imagem"));
                Produtos.add(p); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        }  
        return Produtos;
    }
    
    public void update(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (idUsuario, idCategoria, IdFornecedor, Descrcao, CadigoDeBarras, ValorCusto, ValorVenda, EstoqueMinimo, Quantidade, UnidMedida, Estado, DataCadastro, Imagem) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, p.getIdUsuario());
            stmt.setInt(2, p.getIdCategoria());
            stmt.setInt(3, p.getIdFornecedor());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getCodigoDeBarras());
            stmt.setDouble(6, p.getValorCusto());
            stmt.setDouble(7, p.getValorVenda());
            stmt.setInt(8, p.getEstMinimo());
            stmt.setInt(9, p.getQuantidade());
            stmt.setInt(10, p.getUnidadeDeMedida());
            stmt.setBoolean(11, p.getEstado());
            stmt.setDate(12, (Date) p.getDataCadastro());
            stmt.setString(13, p.getImagem());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" +ex);
        } finally{
            ConnectionFactory.CloseConnection(con, stmt);
        } 
    }
    
}