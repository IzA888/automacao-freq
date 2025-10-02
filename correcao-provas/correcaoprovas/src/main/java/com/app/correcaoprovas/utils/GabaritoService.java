package com.app.correcaoprovas.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.springframework.stereotype.Service;


public class GabaritoService {

    public void IdentificarGabarito(List<File> pdfs){
        if (pdfs.equals("gabarito.pdf")) {
            File parent = pdfs.get(0).getParentFile();
            File gabarito = new File(parent, "gabarito.pdf");
            
            if(!gabarito.exists()){
                JOptionPane.showMessageDialog(null, "Não foi encontrado o arquivo 'gabarito.pdf' na pasta",
                 "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
    public static List<String> gabaritoLista(Mat src) throws Exception{
        List<Rect> bolhas = ArquivoService.encontrarBolhas(src);
        List<String> alternativas = AlternativasService.detectarAlternativas(bolhas);
        List<Boolean> marcadas = AlternativasService.alternativasMarcadas(alternativas);

        //Verificar quais bolhas estão marcadas
       if( alternativas.iterator().hasNext() && marcadas.iterator().hasNext()){
        List<String> gabarito = new ArrayList();
        alternativas.forEach(alt -> gabarito.add(alt));
       }

        return alternativas;

    }

}
