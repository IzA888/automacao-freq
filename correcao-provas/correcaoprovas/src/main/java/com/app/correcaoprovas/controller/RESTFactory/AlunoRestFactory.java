package com.app.correcaoprovas.controller.RESTFactory;

import java.util.ArrayList;
import java.util.List;

import com.app.correcaoprovas.dto.AlunoDto;
import com.app.correcaoprovas.model.Aluno;


public class AlunoRestFactory {
 
    public static AlunoDto toDto(Aluno aluno){
        AlunoDto dto = new AlunoDto();
        dto.setNome(aluno.getNome());
        dto.setTurma(aluno.getTurma());
        dto.setAno(aluno.getAno());
        dto.setNotas(aluno.getNotas());
        return dto;
    }
    
    public static List<AlunoDto> toDto(List<Aluno> Aluno){
        List<AlunoDto> dto = new ArrayList<>();
        dto.get(0).setNome(Aluno.get(0).getNome());
        dto.get(0).setTurma(Aluno.get(0).getTurma());
        dto.get(0).setAno(Aluno.get(0).getAno());
        dto.get(0).setNotas(Aluno.get(0).getNotas());
        return dto;
    }
}
