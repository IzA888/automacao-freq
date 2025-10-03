package com.app.correcaoprovas.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;


public class GabaritoUtils {

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
   
    public static List<String> gabaritoLista() throws Exception{
        List<String> alternativas = AlternativasUtils.detectarAlternativas();
        Map<Integer,List<Boolean>> marcadas = AlternativasUtils.isMarcada();
        List<String> gabarito = new ArrayList<>();

        //Verificar quais bolhas estão marcadas
       if( alternativas.iterator().hasNext() && marcadas.values().iterator().hasNext()){
        //adicionar alternativas ao gabarito
        alternativas.forEach(alt -> gabarito.add(alt));
        return gabarito;
       }

        return gabarito;

    }

}
