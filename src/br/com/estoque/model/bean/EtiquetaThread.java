/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model.bean;

import br.com.estoque.connection.ConnectionFactory;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Rafael
 */
public class EtiquetaThread extends Thread{
    int id_prod = 0;
    int qtd_eti = 0;
    String src = null;
    
    public EtiquetaThread(String src, int id_prod, int qtd_eti){
        this.src = src;
        this.id_prod = id_prod;
        this.qtd_eti = qtd_eti;   
    }
    
    @Override
    public void run(){
        
        Connection conn = ConnectionFactory.getConnection();
        JasperPrint jasperPrint = null;
        Map par = new HashMap();
        par.put("id_prod", id_prod);
        
        try {
           jasperPrint = JasperFillManager.fillReport(src, par, conn);
            //jasperPrint = JasperFillManager.fillReport(src, par, conn);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório. - Erro: "+ex);
        }
       
        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setSize(1024, 800);
        view.setZoomRatio((float) 0.85);
        view.setTitle("Teste");
        view.setVisible(true); 
    }
}
