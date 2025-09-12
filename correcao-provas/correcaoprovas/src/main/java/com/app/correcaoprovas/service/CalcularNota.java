package com.app.correcaoprovas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.correcaoprovas.model.Aluno;
import com.app.correcaoprovas.model.Nota;
import com.app.correcaoprovas.model.Prova;
import com.app.correcaoprovas.service.interfaces.CalcularNotaImp;

@Service
public class CalcularNota implements CalcularNotaImp {

    @Override
    public List<Nota> calcularNotas(List<Prova> provas, List<Aluno> alunos){

        return notasCalculadas;
    }
}