package com.app.correcaoprovas.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;



public class ProvaUtils{

    public static List<File> carregarArquivos(){
        //Carregar pasta com as provas a serem corrigidas

        JFileChooser seletor = new JFileChooser();
        seletor.setDialogTitle("Selecione s pasta com as provas a serem corrigidas(deve conter um arquivo com o nome 'gabarito.pdf' com as repostas)");
        seletor.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        //Abrir seletor de arquivos
        int resultado = seletor.showOpenDialog(null);
        if(resultado != JFileChooser.APPROVE_OPTION){
            System.out.println("Nenhuma pasta selecionada");
        }
        
        File pasta = seletor.getSelectedFile();
    
        List<File> pdfs = new ArrayList<>();

        //Listar arquivos PDF na pasta
        if(pasta != null && pasta.exists() && pasta.isDirectory()){
            for (File pdf : Objects.requireNonNull(pasta.listFiles())){
                if( pdf.isFile() && pdf.getName().toLowerCase().endsWith(".pdf")){
                    pdfs.add(pdf);
                }
            }
        }

        return pdfs;

    }

    // //Transforma PDF e imagem, 1 por vez
    // public static Mat carregarImg() throws Exception{

    //     var documento = PDDocument.load(pdfFile);
    //     var renderer = new PDFRenderer(documento);
    //     var imagem = renderer.renderImage(0, 300); 
    //     File imgFile = new File("temp.png");
    //     ImageIO.write(imagem, ".png", imgFile);
    //     documento.close();
        
    //     Mat src = opencv_imgcodecs.imread(imgFile.getAbsolutePath(), 
    //         opencv_imgcodecs.IMREAD_GRAYSCALE);

    //     if(src.empty()){
    //         throw new RuntimeException("Erro: " + imgFile.getAbsolutePath());
    //     }

    //     return src;
    // }

    // //Encontrar bolhas em 1 imagem por vez
    // public static List<Rect> encontrarBolhas() throws Exception{

    //     List<Rect> bolhas = new ArrayList<>();

    //     //Binarização
    //     Mat bin = new Mat();
    //     opencv_imgproc.threshold(carregarImg(), bin, 128, 255, opencv_imgproc.THRESH_BINARY_INV);

    //     //Encontrar contornos
    //     MatVector contornos = new MatVector();
    //     opencv_imgproc.findContours(
    //         bin, contornos, 
    //         opencv_imgproc.RETR_EXTERNAL, 
    //         opencv_imgproc.CHAIN_APPROX_SIMPLE);
        
    //     return bolhas;
    // }

    public static String salvarResultadosExcel( Map<String, Double> resultado) throws Exception{
        File pastaSaida = new File("resultados");
        if(!pastaSaida.exists()) pastaSaida.mkdirs();


        if(!resultado.isEmpty()){ //Salvar respostas corrigidas por ordem de turma/ano
            File resultados = new File(pastaSaida, pastaSaida.getName().replace(".pdf", "resultado.xlsx"));// salvar em formato excel
            return resultados.getAbsolutePath();
        } else {
            throw new RuntimeException("Nenhum resultado para salvar");
        }
    }

    public static void compactarArquivos(File pastaSaida) throws FileNotFoundException, IOException{
        File zipFile = new File("resultados.zip");
        try (FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(fos)) {
            for (File f : Objects.requireNonNull(pastaSaida.listFiles())){
                try (FileInputStream fis = new FileInputStream(f)){
                    ZipEntry zipEntry = new ZipEntry(f.getName());
                    zos.putNextEntry(zipEntry);

                    byte[] buffer = new byte[1024];
                    int len;
                    while((len = fis.read(buffer)) > 0){
                        zos.write(buffer, 0, len);
                    }
                }
            }
        }

    }
}