package com.app.correcaoprovas.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;


public class AlternativasUtils {


    // Transforma todos os PDFs em imagens usando map
    public static List<Mat> carregarImgs() throws Exception {
        return ProvaUtils.carregarArquivos().stream().map(pdfFile -> {
            try {
                var documento = PDDocument.load(pdfFile);
                var renderer = new PDFRenderer(documento);
                var imagem = renderer.renderImage(0, 300);
                File imgFile = new File("temp_" + pdfFile.getName() + ".png");
                ImageIO.write(imagem, "png", imgFile);
                documento.close();

                Mat src = opencv_imgcodecs.imread(imgFile.getAbsolutePath(),
                        opencv_imgcodecs.IMREAD_GRAYSCALE);

                if (src.empty()) {
                    throw new RuntimeException("Erro: " + imgFile.getAbsolutePath());
                }

                return src;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }


    //Encontrar bolhas em todas as imagens usando map
    public static List<Rect> encontrarBolhas() throws Exception {
        return carregarImgs().stream().flatMap(img -> {
            // Binarização
            Mat bin = new Mat();
            org.bytedeco.opencv.global.opencv_imgproc.threshold(
                img, bin, 128, 255, org.bytedeco.opencv.global.opencv_imgproc.THRESH_BINARY_INV);

            // Encontrar contornos
            MatVector contornos = new MatVector();
            org.bytedeco.opencv.global.opencv_imgproc.findContours(
                bin, contornos,
                org.bytedeco.opencv.global.opencv_imgproc.RETR_EXTERNAL,
                org.bytedeco.opencv.global.opencv_imgproc.CHAIN_APPROX_SIMPLE);

            List<Rect> bolhas = new ArrayList<>();
            for (int i = 0; i < contornos.size(); i++) {
                Mat contorno = contornos.get(i);
                Rect boundingRect = org.bytedeco.opencv.global.opencv_imgproc.boundingRect(contorno);
                bolhas.add(boundingRect);
            }
            return bolhas.stream();
        }).collect(Collectors.toList());
    }

    public static List<String> detectarAlternativas() throws Exception {
        
        List<Rect> bolhas = encontrarBolhas();
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
        
        //Retorna lista de alternativas
        return alternativas;
    }

    public static Map<Integer, List<Boolean>> isMarcada() throws Exception{
        List<List<Boolean>> alternativasMarcadas = ProvaUtils.carregarArquivos().stream().map( pdf -> {
            try {
                List<Mat> src = carregarImgs();

                List<Rect> bolhas = encontrarBolhas();
        
                List<Boolean> marcadas = new ArrayList();
        
                // Verificar quais bolhas estão marcadas para todas as imagens
                for (int i = 0; i < src.size(); i++) {
                    Mat img = src.get(i);
                    for (Rect b : bolhas) {
                        Mat bolha = new Mat(img, b);

                        double cheio = opencv_core.countNonZero(bolha);
                        double area = b.width() * b.height();
                        double proporcao = cheio / area;

                        marcadas.add(proporcao > 0.3);
                    }
                }

                //Retorna lista de alternativas marcadas
                return marcadas;

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toList();
        return alternativasMarcadas.stream()
                .collect(Collectors.toMap(
                    m -> alternativasMarcadas.indexOf(m) + 1,
                    Function.identity()
                ));
    }
}
