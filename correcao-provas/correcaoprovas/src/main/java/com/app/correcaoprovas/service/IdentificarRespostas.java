package com.app.correcaoprovas.service;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;

public class IdentificarRespostas {
    
    public void identificarResp(String imgPath){
        // Carrega imagem em escala de cinza
        Mat src = opencv_imgcodecs.imread(imgPath, opencv_imgcodecs.IMREAD_GRAYSCALE);
        
        if(src.empty()){
            System.out.println("Não foi possível carregar imagem: " + imgPath);
            return ;
        }

        
        // Binarização (inverte preto/branco)
        opencv_imgproc.threshold(src, src, 128, 255, opencv_imgproc.THRESH_BINARY_INV);

        // Encontrar contornos (possíveis bolhas)
        MatVector contours = new MatVector();

        opencv_imgproc.findContours(src.clone(), contours, new Mat(),
                                     opencv_imgproc.RETR_EXTERNAL, opencv_imgproc.CHAIN_APPROX_SIMPLE);
        
        List<Rect> marcadas = new ArrayList();

        for (int i=0; i < contours.size(); i++){
            Rect rect = opencv_imgproc.boundingRect(contours.get(i));

            // filtro para descartar ruídos (ajuste os limites conforme sua prova)
            if(rect.width() > 20 && rect.width() < 100 && rect.height() > 20 && rect.height < 100){
                Mat bolha = new Mat(src, rect);

                double cheio = opencv_core.countNonZero(bolha);
                double area = rect.width() * rect.height();
                double proporcao = cheio/area;

                // Se mais da metade estiver preenchida, consideramos "marcada"
                if(proporcao > 0.5){
                    marcadas.add(rect);
                    System.out.println("Bolha marcada posição: x=" + rect.x());
                }
            }
        }

        System.out.println("Total bolhas marcadas: " + marcadas.size());
    }
}
