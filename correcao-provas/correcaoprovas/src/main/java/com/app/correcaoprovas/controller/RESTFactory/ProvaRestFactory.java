package com.app.correcaoprovas.controller.RESTFactory;

import java.util.ArrayList;
import java.util.List;

import com.app.correcaoprovas.dto.ProvaDto;
import com.app.correcaoprovas.model.Prova;

public class ProvaRestFactory {
    
    public static ProvaDto toDto(Prova prova){
        ProvaDto dto = new ProvaDto();
        dto.setTurma(prova.getTurma());
        dto.setMateria(prova.getMateria());
        dto.setAno(prova.getAno());
        dto.setQuestao(prova.getQuestoes());
        dto.setResposta(prova.getAlternativas());
        return dto;
    }
    
    public static List<ProvaDto> toDto(List<Prova> prova){
        List<ProvaDto> dto = new ArrayList<>();
        dto.get(0).setTurma(prova.get(0).getTurma());
        dto.get(0).setMateria(prova.get(0).getMateria());
        dto.get(0).setAno(prova.get(0).getAno());
        dto.get(0).setQuestao(prova.get(0).getQuestoes());
        dto.get(0).setResposta(prova.get(0).getAlternativas());
        return dto;
    }
}
