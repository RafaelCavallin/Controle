
package br.com.estoque.connection;

import br.com.estoque.model.bean.Usuario;

public class Sessao {
    private static Sessao instance = null;
    private Usuario usuario;
    
    private Sessao(){
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public String getTipo(){
        return usuario.getTipo();
    }
    
    public int getIdUser(){
        return usuario.getIdUsuario();
    }
    
    public static Sessao getInstance(){
        if(instance == null){
            instance = new Sessao();
        }
        return instance;
    }
}