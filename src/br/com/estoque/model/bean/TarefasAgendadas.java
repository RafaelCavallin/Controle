package br.com.estoque.model.bean;

import br.com.estoque.connection.ConnectionFactory;
import java.io.File;
import java.sql.Connection;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import org.apache.commons.mail.EmailException;


public class TarefasAgendadas {
    
     public void enviaRelatorioDiario() throws EmailException{
        Connection conn = ConnectionFactory.getConnection();
        JasperPrint jasperPrint = null;
        String src = "Relatorios\\Produtos\\RelatorioProdutosBaixoEstoque.jasper";
               
        try {
             jasperPrint = JasperFillManager.fillReport(src, null, conn);
             JasperExportManager.exportReportToPdfFile(jasperPrint,"RelatoriosPorEmail\\RelatorioDiario.pdf");
             
             File file = new File("RelatoriosPorEmail\\RelatorioDiario.pdf");
             RelatorioPorEmail relEmail = new RelatorioPorEmail();
             relEmail.sendAttachMail("RelatoriosPorEmail\\RelatorioDiario.pdf");
 
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório. - Erro: "+ex);
        }
     }
}
