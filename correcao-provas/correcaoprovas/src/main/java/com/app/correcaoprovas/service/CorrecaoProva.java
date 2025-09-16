package com.app.correcaoprovas.service;

import java.io.File;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.springframework.stereotype.Service;

import com.app.correcaoprovas.model.Prova;
import com.app.correcaoprovas.service.interfaces.CorrecaoProvasImp;

@Service
public class CorrecaoProva implements CorrecaoProvasImp {
    
    @Override
    public List<Prova> corrigirProvas(Prova prova) {
        
        MatVector contornos = ArquivoService.encontrarContornos();

        List<Rect> marcadas = RespostaService.identificarResp();

        String questoes = AlternativasService.detectarAlternativas();
        for (Rect b : GabaritoService.gabaritoLista()){

            String resultado = "—";
            if (marcadas && b.correta) resultado = "Correto";
            else if (marcadas && !b.correta) resultado = "Errado";
    
            List<Prova> provasCorrigidas = writer.write(b.questao + "," +
                        b.alternativa + "," +
                        (b.correta ? "X" : "") + "," +
                        resultado + "\n");

            return provasCorrigidas;
        }
    }


}
