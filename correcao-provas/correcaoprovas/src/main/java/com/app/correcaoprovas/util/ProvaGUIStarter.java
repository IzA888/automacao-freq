package com.app.correcaoprovas.util;

import javax.swing.SwingUtilities;

import org.springframework.stereotype.Component;

import com.app.correcaoprovas.service.ProvasGUI;

import jakarta.annotation.PostConstruct;

@Component
public class ProvaGUIStarter {
    
    @PostConstruct
    public void startGUI(){
        SwingUtilities.invokeLater(() -> {
            ProvasGUI gui = new ProvasGUI();
            gui.setVisible(true);
        });
    }
}
