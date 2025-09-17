package com.app.correcaoprovas.GUI;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.*;

import com.app.correcaoprovas.service.ArquivoService;

public class ProvasGUI extends JFrame{
    private  JButton btnSelecionarPasta;
    private JButton btnProcessar;
    private JTextArea logArea;
    private File pastaSelecionada;

    public ProvasGUI(){
        setTitle("Correção de provas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnSelecionarPasta = new JButton("Selecionar pasta");
        btnProcessar = new JButton("Processar provas");
        logArea = new JTextArea();
        logArea.setEditable(false);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(btnSelecionarPasta);
        panel.add(btnProcessar);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        //Ação selecionar pasta
        btnSelecionarPasta.addActionListener(e -> {
            pastaSelecionada = ArquivoService.carregarArquivos();
            log("Pasta selecionada: " + pastaSelecionada.getAbsolutePath());
        });

        btnProcessar.addActionListener(e -> {
            var pdfs = ArquivoService.extrairPdfs();
            if( pdfs != null){
                pdfs.stream().forEach(pdf -> log("Processando pasta selecionada: " + pdf));                
            }else {
                throw new RuntimeException("Erro");
            }
            log("Processamento Concluído");

        });
    }

    private void log(String msg){
        logArea.append(msg + "\n");
    }
    
}
