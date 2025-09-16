package com.app.correcaoprovas.service;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.springframework.stereotype.Service;

@Service
public class GabaritoService {

    public void IdentificarGabarito(){
        File gabarito = new File(ArquivoService.carregarArquivos(), "gabarito.pdf");
        if(!gabarito.exists()){
            JOptionPane.showMessageDialog(null, "Não foi encontrado o arquivo 'gabarito.pdf' na pasta",
             "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
   
    public static List<Rect> gabaritoLista(){
    
        int questao = 1;
        for (Map.Entry<Integer, List<Rect>> entry > linhas linhas.entrySet()){
            List<Rect> linha = entry.getValue();
            linha.sort(Comparator.comparingInt(Rect::x));
    
            for (int i=0; i< linha.size() && i< alternativas.length; i++){
                    Rect r = linha.get(i);
    
                    double cheio = opencv_core.countNonZero(bolha);
                    double area = rect.width() * rect.height();
                    double proporcao = cheio/area;
    
                    // Se mais da metade estiver preenchida, consideramos "marcada"
                    Boolean marcada = proporcao > 0.5;
                    List<Rect> gabaritoLayout;
                    return gabaritoLayout.add(new marcadas(questoes, alternativas[i], r, marcadas));
    
                }
    
        }

    }

}
