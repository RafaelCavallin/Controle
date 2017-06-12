package br.com.estoque.model.dao;

import br.com.estoque.connection.ConnectionFactory;
import br.com.estoque.model.bean.Produto;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    public void create(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (idProduto, idUsuario, idCategoria, IdFornecedor, Descricao, CodigoDeBarras, ValorCusto, ValorVenda, EstoqueMinimo, Quantidade, UnidMedida, Estado, DataCadastro, Imagem) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, p.getIdProduto());
            stmt.setInt(2, p.getIdUsuario());
            stmt.setInt(3, p.getIdCategoria());
            stmt.setInt(4, p.getIdFornecedor());
            stmt.setString(5, p.getDescricao());
            stmt.setString(6, p.getCodigoDeBarras());
            stmt.setBigDecimal(7, p.getValorCusto());
            stmt.setBigDecimal(8, p.getValorVenda());
            stmt.setInt(9, p.getEstMinimo());
            stmt.setInt(10, p.getQuantidade());
            stmt.setString(11, p.getUnidadeDeMedida());
            stmt.setBoolean(12, p.getEstado());
            
            // Convertendo LocalDate em data para enviar para o banco
            stmt.setDate(13, Date.valueOf(p.getDataCadastro()));
            stmt.setString(14, p.getImagem());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar! - Contate o administrador - Erro: " +ex);
        } finally{
            ConnectionFactory.CloseConnection(con, stmt);
        } 
    }
    
    public void delete(int idProd){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE idProduto = ?");
            stmt.setInt(1, idProd);
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Falha ao excluir!" +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt);
        }
    }
    
    public Produto readForId(int id){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        Produto p = new Produto();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE idProduto = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                p.setIdProduto(rs.getInt("idProduto"));
                p.setIdUsuario(rs.getInt("idUsuario"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdFornecedor(rs.getInt("idFornecedor"));
                p.setDescricao(rs.getString("Descricao"));
                p.setCodigoDeBarras(rs.getString("CodigoDeBarras"));
                p.setValorCusto(rs.getBigDecimal("ValorCusto"));
                p.setValorVenda(rs.getBigDecimal("ValorVenda"));
                p.setEstMinimo(rs.getInt("EstoqueMinimo"));
                p.setQuantidade(rs.getInt("Quantidade"));
                p.setUnidadeDeMedida(rs.getString("UnidMedida"));
                p.setEstado(rs.getBoolean("Estado"));
                // Convertendo a data do banco em LocalDate para o objeto
                LocalDate dataCad = rs.getDate("DataCadastro").toLocalDate();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
                String dataEmString = dataCad.format(formatador);
                
                LocalDate dataCadConvertida= LocalDate.parse(dataEmString, formatador);
                
                p.setDataCadastro(dataCadConvertida);
                p.setImagem(rs.getString("Imagem"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar! - " +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        }
        return p;
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
                p.setValorCusto(rs.getBigDecimal("ValorCusto"));
                p.setValorVenda(rs.getBigDecimal("ValorVenda"));
                p.setEstMinimo(rs.getInt("EstoqueMinimo"));
                p.setQuantidade(rs.getInt("Quantidade"));
                p.setUnidadeDeMedida(rs.getString("UnidMedida"));
                p.setEstado(rs.getBoolean("Estado"));
                // Convertendo a data do banco em LocalDate para o objeto
                LocalDate dataCad = rs.getDate("DataCadastro").toLocalDate();
                DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                
                String dataEmString = dataCad.format(formatador);
                
                LocalDate dataCadConvertida= LocalDate.parse(dataEmString, formatador);
                
                p.setDataCadastro(dataCadConvertida);
                p.setImagem(rs.getString("Imagem"));
                Produtos.add(p); 
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar! - " +ex.getLocalizedMessage());
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        }  
        return Produtos;
    }
    
    public int readForDesc(String descricao){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = 0;
        
        try {
            stmt = con.prepareCall("SELECT * FROM produtos WHERE Descricao = ?");
            stmt.setString(1, descricao);
            rs = stmt.executeQuery();
                
            while (rs.next()) {                
                id = rs.getInt("idProduto");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar ID do produto ao procurar por descrição! - " + ex);
        }
        
        return id;
    }
    
    public void update(Produto p){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produtos (idUsuario, idCategoria, IdFornecedor, Descricao, CadigoDeBarras, ValorCusto, ValorVenda, EstoqueMinimo, Quantidade, UnidMedida, Estado, DataCadastro, Imagem) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, p.getIdUsuario());
            stmt.setInt(2, p.getIdCategoria());
            stmt.setInt(3, p.getIdFornecedor());
            stmt.setString(4, p.getDescricao());
            stmt.setString(5, p.getCodigoDeBarras());
            stmt.setBigDecimal(6, p.getValorCusto());
            stmt.setBigDecimal(7, p.getValorVenda());
            stmt.setInt(8, p.getEstMinimo());
            stmt.setInt(9, p.getQuantidade());
            stmt.setString(10, p.getUnidadeDeMedida());
            stmt.setBoolean(11, p.getEstado());
            stmt.setDate(12, Date.valueOf(p.getDataCadastro()));
            stmt.setString(13, p.getImagem());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!" +ex);
        } finally{
            ConnectionFactory.CloseConnection(con, stmt);
        } 
    }
    
    @SuppressWarnings("empty-statement")
    public void CopyImg(String origemArquivo, String destinoArquivo ) throws IOException{
        
        File origem = new File(origemArquivo);
        File destino = new File(destinoArquivo + "\\" + origem.getName());
        
        //System.out.println(destinoArquivo + "\\" + origem.getName());
        //System.out.println(origemArquivo);
        
        if(destino.exists()){
            destino.delete();
        }
        FileChannel origemChannel = null;
        FileChannel destinoChannel = null;
        
        try {
            origemChannel = new FileInputStream(origem).getChannel();
            destinoChannel = new FileOutputStream(destino).getChannel();
            origemChannel.transferTo(0, origemChannel.size(), destinoChannel);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao transferir imagem - " + ex);
        }finally{
            if(origemChannel != null && origemChannel.isOpen()){
                origemChannel.close();
            }
            if(destinoChannel != null && destinoChannel.isOpen()){
                destinoChannel.close();;
            }     
        }   
    } 
    
    public List<Produto> readProdForDesc(String busca){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> Produto = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE Descricao LIKE ?");
            stmt.setString(1, "%"+ busca +"%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                Produto p = new Produto();
                p.setIdProduto(rs.getInt("idProduto"));
                p.setIdCategoria(rs.getInt("idCategoria"));
                p.setIdUsuario(rs.getInt("idUsuario"));
                p.setIdFornecedor(rs.getInt("idFornecedor"));
                p.setDescricao(rs.getString("Descricao"));
                p.setCodigoDeBarras(rs.getString("CodigoDeBarras"));
                
                BigDecimal bigValorCusto = new BigDecimal(rs.getString("ValorCusto"));
                p.setValorCusto(bigValorCusto);
                
                BigDecimal bigValorVenda = new BigDecimal(rs.getInt("ValorVenda"));
                p.setValorVenda(bigValorVenda);
                
                p.setEstMinimo(rs.getInt("EstoqueMinimo"));
                p.setQuantidade(rs.getInt("Quantidade"));
                p.setUnidadeDeMedida(rs.getString("UnidMedida"));
                p.setEstMinimo(rs.getInt("Estado"));
                p.setDataCadastro(rs.getDate("DataCadastro").toLocalDate());
                p.setImagem(rs.getString("Imagem"));
                
                Produto.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar! (Lista) - " +ex);
        }finally{
            ConnectionFactory.CloseConnection(con, stmt, rs);
        }
        return Produto;      
    }
    
    public void addEstProd(int qtdAdd, int idProd){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int qtdAtual = 0;
        
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE idProduto = ?");
            stmt.setInt(1,idProd);
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                qtdAtual =rs.getInt(10);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível validar a quantidade atual do estoque - Contate o administrador do sistema -> "+ex);
        }
        
        qtdAtual = (qtdAtual + qtdAdd);
        
        try {
            stmt = con.prepareStatement("UPDATE produtos SET Quantidade = ? WHERE idProduto = ?");
            stmt.setInt(1, qtdAtual);
            stmt.setInt(2, idProd);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Adicionado com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível adicionar ao estoque - Contate o administrador do sistema -> "+ex);
        }
    }
    
    public void removeEstProd(int qtdRemove, int idProd){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int qtdAtual = 0;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE idProduto = ?");
            stmt.setInt(1,idProd);
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                qtdAtual =rs.getInt(10);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível validar a quantidade atual do estoque - Contate o administrador do sistema -> "+ex);
        }
        
        qtdAtual = (qtdAtual - qtdRemove);
        
        try {
            stmt = con.prepareStatement("UPDATE produtos SET Quantidade = ? WHERE idProduto = ?");
            stmt.setInt(1, qtdAtual);
            stmt.setInt(2, idProd);
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Removido com sucesso!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível adicionar ao estoque - Contate o administrador do sistema -> "+ex);
        }
    }
}