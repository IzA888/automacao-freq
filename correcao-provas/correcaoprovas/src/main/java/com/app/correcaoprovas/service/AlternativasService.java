package com.app.correcaoprovas.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.springframework.stereotype.Service;

@Service
public class AlternativasService {

    // public static String detectarAlternativas() {
    //     Map<Integer, List<Rect>> linhas = new TreeMap();
    //     int toleranciaY = 20; //Tolerância vertical

    //     for(Rect r : bolhas) {
    //         int yBase = (r.y() / toleranciaY) * toleranciaY;
    //         linhas.computeIfAbsent(yBase, y -> new ArrayList<>()).add(r);
    //     }

    //     //Processar cada linha
    //     int questao = 1;
    //     char[] alternativas = {'A', 'B', 'C', 'D'};

    //     for (Map.Entry<Integer, List<Rect>> entry : linhas.entrySet()) {
    //         List<Rect> linha = entry.getValue();

    //         //Ordenar bolhas
    //         linha.sort(Comparator.comparingInt(Rect::x));

    //         for (int i=0; i< linha.size() && i< alternativas.length; i++){
    //             Rect r = linha.get(i);
    //             System.out.println("Questão " + questao + "-> " + alternativas[i]);
    //         }

    //         questao++;
    //     }
    // }

    public static List<Boolean> alternativasMarcadas(List<Rect> bolhas) throws Exception{
        Mat src = ArquivoService.carregarImg(ArquivoService.carregarArquivos());

        List<Boolean> marcadas = new ArrayList();

        for (Rect b : bolhas){
            Mat bolha = new Mat(src, b);

            double cheio = opencv_core.countNonZero(bolha);
            double area = b.width() * b.height();
            double proporcao = cheio/area;

            marcadas.add(proporcao > 0.3);
        }

        return marcadas;
    }
}
