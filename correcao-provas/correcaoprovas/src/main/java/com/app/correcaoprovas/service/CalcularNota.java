package com.app.correcaoprovas.service;

import java.util.Map;

import org.springframework.stereotype.Service;


@Service
public class CalcularNota {
    /**
     * 
     * @param correcao
     * @param valorQuestao
     * @return total de pontos
     */

    public Double calcularNotas(Map<Integer, Boolean> correcao, Double valorQuestao){
        long acertos =  correcao.values().stream()
                        .filter(Boolean::booleanValue)
                        .count();
                    
        return acertos * valorQuestao;
    }
}