package com.app.correcaoprovas.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.swing.JOptionPane;

import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.correcaoprovas.dto.ProvaDto;
import com.app.correcaoprovas.model.Prova;

@Service
public class CorrecaoProva {
    /**
     * 
     * @param respostas respostas marcadas na prova
     * @param gabarito reposta corretas
     * @return a reposta marcada e se ela está correta
     */

    private Prova prova;

    public Map<Integer, Boolean> corrigirProvas(List<String> respostas, Map<Integer, String> gabarito) {
        Map<Integer, Boolean> resultado = new HashMap<>();
        
        //Resposta por questão
        Map<Integer, List<String>> porQuestao = new HashMap<>();
        for(String r : respostas){
            porQuestao.computeIfAbsent(prova.getQuestoes(), k -> new ArrayList<>()).add(r);
        }

        //Verifica cada questao
        for (Map.Entry<Integer, List<String>> entry : porQuestao.entrySet()){
            int questao = entry.getKey();
            List<String> alternativas = entry.getValue();

            //Pega as marcadas
            Optional<String> marcada = alternativas.stream().findFirst();
                        
            if(marcada.isPresent()){
                String respostaAluno = marcada.get();
                String correta = gabarito.get(questao);
                resultado.put(questao, respostaAluno.equals(correta));
            } else {
                resultado.put(questao, false);// não marcou = errada
            }
        }

        return resultado;
    }


}
