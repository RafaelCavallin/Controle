/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estoque.model.bean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.Timer;

/**
 *
 * @author Rafael
 */
public class TarefasAgendadas {

    private Timer timer;
    
    
    public void task(){
        ActionListener action = (ActionEvent e) -> {
            LocalDateTime hora = LocalDateTime.now();
            System.out.println("DEU" + hora);
            
        };   
        this.timer = new Timer(50000, action);   
        this.timer.start();   
    }
    
    
}
