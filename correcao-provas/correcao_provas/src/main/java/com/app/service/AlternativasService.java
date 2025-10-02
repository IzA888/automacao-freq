package com.app.correcaoprovas.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate.BooleanOperator;

@Service
public class AlternativasService {

    public static List<String> detectarAlternativas(List<Rect> bolhas) {
        Map<Integer, List<Rect>> linhas = new TreeMap();
        int toleranciaY = 20; //Tolerância vertical

        //Agrupar bolhas por linhas
        for(Rect r : bolhas) {
            int yBase = (r.y() / toleranciaY) * toleranciaY;
            linhas.computeIfAbsent(yBase, y -> new ArrayList<>()).add(r);
        }

        //Processar cada linha
        int questao = 1;
        List<String> alternativas = List.of("A", "B", "C", "D");

        //ordenar linha por y
        for (Map.Entry<Integer, List<Rect>> entry : linhas.entrySet()) {
            List<Rect> linha = entry.getValue();

            //Ordenar bolhas
            linha.sort(Comparator.comparingInt(Rect::x));

            //Mapear bolhas para alternativas
            for (int i=0; i< linha.size() && i< alternativas.size(); i++){
                Rect r = linha.get(i);
                alternativas.set(i, alternativas.get(i));

            }
          
            questao++;
        }
        
        return alternativas;
    }

    public static List<Boolean> alternativasMarcadas(List<String> alternativas) throws Exception{
        List<List<Boolean>> alternativasMarcadas = ArquivoService.carregarArquivos().stream().map( pdf -> {
            try {
                Mat src = ArquivoService.carregarImg(pdf);
                //Mat src = ArquivoService.carregarImg(ArquivoService.carregarArquivos());
                List<Rect> bolhas = ArquivoService.encontrarBolhas(src);
        
                List<Boolean> marcadas = new ArrayList();
        
                //Verificar quais bolhas estão marcadas
                for (Rect b : bolhas){
                    Mat bolha = new Mat(src, b);
        
                    double cheio = opencv_core.countNonZero(bolha);
                    double area = b.width() * b.height();
                    double proporcao = cheio/area;
        
                    marcadas.add(proporcao > 0.3);
                }
                
                return marcadas;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return alternativasMarcadas.stream().flatMap(List::stream).toList();
    }
}
