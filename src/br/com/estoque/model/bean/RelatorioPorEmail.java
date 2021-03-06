package br.com.estoque.model.bean;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class RelatorioPorEmail {
    
    public String from = "SEU_EMAIL";
    public String to = null;
    public String senhaMail = "SENHA_DO_EMAIL";
    
    LocalDate hoje = LocalDate.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public RelatorioPorEmail() throws IOException{
        ConfigSistema cam = new ConfigSistema();
        this.to = cam.caminhoConfig("3");
    }

    public void sendSimplemail() throws IOException {  
        
        Email email = new SimpleEmail();
 
        try {
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(from, senhaMail));
            email.setSSLOnConnect(true);
            email.addTo(to, "Rafael Cavallin");
            email.setFrom(from , "Sistema Estoque");
            email.setSubject("Relatório Diário - " + hoje.format(formato));
            email.setMsg("Relatório diário do seu estoque.");

            email.send();
        } catch (EmailException e) {
            System.out.println(e.getMessage());
        }
    }
  
    public void sendAttachMail(String src) throws EmailException{
        MultiPartEmail email = new MultiPartEmail();
        
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(src);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Relatório diário");
        attachment.setName(hoje.format(formato)+".pdf");

        email.attach(attachment);
        try {
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(from, senhaMail));
            email.setSSLOnConnect(true);
            email.addTo(to, "Rafael Cavallin");
            email.setFrom(from, "Sistema Estoque");
            email.setSubject("Relatório Diário - " + hoje.format(formato));
            email.setMsg("Relatório diário do seu estoque.");
            
            email.send();
        } catch (EmailException e) {
            System.out.println(e.getMessage());
        }
    }
}
