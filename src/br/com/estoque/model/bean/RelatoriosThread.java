package br.com.estoque.model.bean;

import br.com.estoque.connection.ConnectionFactory;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Rafael
 */
public class RelatoriosThread extends Thread{
        
    private String src;
    private String titulo;
    
    public RelatoriosThread(String src, String titulo){
        this.src = src;
        this.titulo = titulo;
    }
    
    @Override
    public void run(){
        Connection conn = ConnectionFactory.getConnection();
        JasperPrint jasperPrint = null;
        
        try {
            jasperPrint = JasperFillManager.fillReport(src, null, conn);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório. - Erro: "+ex);
        }
        
        JasperViewer view = new JasperViewer(jasperPrint, false);
        view.setSize(1024, 800);
        view.setZoomRatio((float) 0.85);
        view.setTitle(titulo);
        view.setVisible(true); 
    }
}
