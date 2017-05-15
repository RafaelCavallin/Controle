/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model.bean;

import java.io.File;
import java.security.NoSuchProviderException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;


/**
 *
 * @author Rafael
 */
    public class EmailRelatorios {
    public static boolean validaEmail(String email) {
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher m = p.matcher(email);
        if (m.matches()) {
            return false;
        } else {
            return true;
        }
    }
    
    public void enviaEmail(){
        
    }
    
    public static void conectaEmail() throws EmailException, NoSuchProviderException, MessagingException {
        String hostname = "smtp.gmail.com";
        String username = "rafael.cavallin89@gmail.com";
        String password = "Liziane190713";
        String emailOrigem = "rafael.cavallin89@gmail.com";
        HtmlEmail email = new HtmlEmail();
        email.setHostName(hostname);
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.addTo("rafael_cavallin@hotmail.com");
        email.setTLS(true);
        email.setFrom(emailOrigem, "Destinatário");
        email.setDebug(true);
        email.setCharset(HtmlEmail.ISO_8859_1);
        Properties props = new Properties();
        props.setProperty(hostname, "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "" + 587);
        props.setProperty("mail.smtp.starttls.enable", "true");
        Session mailSession = Session.getInstance(props, new DefaultAuthenticator(username, password));
        email.setMailSession(mailSession);
        email.send();
        
    } 

}
