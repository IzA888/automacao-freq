package com.app.correcaoprovas.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.lang.String;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.app.correcaoprovas.service.IdentificarRespostas;

@Service
public class ExtrairPdf{

    File pasta = new File("C:/Documents/provas");

    if(pasta.exists() && pasta.isDirectory()) {
        PDDocument[] arquivos = pasta.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".pdf"));

        if (arquivos.length != 0){
            for (PDDocument pdf : arquivos){
                PDFRenderer renderer = new PDFRenderer(pdf);

                for (int page = 0; page < pdf.getNumberOfPages(); page++){
                    BufferedImage image = renderer.renderImageWithDPI(page, 300);
                    File imgFile = new File("page-" + page + ".png");
                    ImageIO.write(image, "png", imgFile);

                    IdentificarRespostas.identificarResp(imgFile.getAbsolutePath());
                }
            }
        } else {
            throw new IOException();
        }
    }

}