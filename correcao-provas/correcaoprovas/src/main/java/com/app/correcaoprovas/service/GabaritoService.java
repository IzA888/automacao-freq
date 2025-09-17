package com.app.correcaoprovas.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;
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
   
    public static List<String> gabaritoLista(Mat src, List<String> alternativas){
        Map<Integer, List<Rect>> linhas = (Map<Integer, List<Rect>>) ArquivoService.encontrarBolhas(src);

        
        int questao = 1;
        List<String> questoesMarcadas = new ArrayList<>();

        for (Map.Entry<Integer, List<Rect>> entry : linhas.entrySet()){
            List<Rect> linha = entry.getValue();
            //Ordenar da esquerda-direita
            linha.sort(Comparator.comparingInt(Rect::x));
    
            for (int i=0; i< linha.size() && i< alternativas.size(); i++){
                    Rect r = linha.get(i);

                    //Recorta a bolha
                    Mat bolha = new Mat(src, r);
    
                    //Conta quantos pixels estão pretos
                    double cheio = opencv_core.countNonZero(bolha);
                    double area = r.width() * r.height();
                    double proporcao = cheio/area;
    
                    // Se mais da metade estiver preenchida, consideramos "marcada"
                    if( proporcao > 0.5){
                        //Salva resultado
                        questoesMarcadas.add(bolha.toString());

                    }
    
                }
                questao++;
        }
        return questoesMarcadas;

    }

}
