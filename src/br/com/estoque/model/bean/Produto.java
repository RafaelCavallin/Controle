package br.com.estoque.model.bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Produto {
    
    private int IdProduto;
    private int IdUsuario;
    private int IdCategoria;
    private int IdFornecedor;
    private String Descricao;
    private String CodigoDeBarras;
    private BigDecimal ValorCusto;
    private BigDecimal ValorVenda;
    private int EstMinimo;
    private int Quantidade;
    private String UnidadeDeMedida;
    private Boolean Estado;
    private LocalDate DataCadastro;
    private String Imagem;

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }
    
    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }
    
    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public int getIdFornecedor() {
        return IdFornecedor;
    }

    public void setIdFornecedor(int IdFornecedor) {
        this.IdFornecedor = IdFornecedor;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public String getCodigoDeBarras() {
        return CodigoDeBarras;
    }

    public void setCodigoDeBarras(String CodigoDeBarras) {
        this.CodigoDeBarras = CodigoDeBarras;
    }

    public BigDecimal getValorCusto() {
        return ValorCusto;
    }

    public void setValorCusto(BigDecimal ValorCusto) {
        this.ValorCusto = ValorCusto;
    }

    public BigDecimal getValorVenda() {
        return ValorVenda;
    }

    public void setValorVenda(BigDecimal ValorVenda) {
        this.ValorVenda = ValorVenda;
    }

    public int getEstMinimo() {
        return EstMinimo;
    }

    public void setEstMinimo(int EstMinimo) {
        this.EstMinimo = EstMinimo;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public String getUnidadeDeMedida() {
        return UnidadeDeMedida;
    }

    public void setUnidadeDeMedida(String UnidadeDeMedida) {
        this.UnidadeDeMedida = UnidadeDeMedida;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean Estado) {
        this.Estado = Estado;
    }

    public LocalDate getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(LocalDate DataCadastro) {
        this.DataCadastro = DataCadastro;
    }

    public String getImagem() {
        return Imagem;
    }

    public void setImagem(String Imagem) {
        this.Imagem = Imagem;
    } 
}
