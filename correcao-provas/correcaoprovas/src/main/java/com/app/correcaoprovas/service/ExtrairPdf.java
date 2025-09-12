package com.app.correcaoprovas.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class ExtrairPdf{

    File pasta = new File("C:/Documents/provas");

    if (pasta.exists() && pasta.isDirectory()){
        File[] arquivos = pasta.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".pdf"));

        if (arquivos.length() != null){
            for (File pdf : arquivos){
                String conteudo = extrairTexto(pdf);
            }
        } else {
            throw new IOException() ;
        }
    }

    private String extrairTexto(File pdf) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'extrairTexto'");
    }
}