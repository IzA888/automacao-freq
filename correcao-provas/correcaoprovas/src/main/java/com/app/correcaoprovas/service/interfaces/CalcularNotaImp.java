package com.app.correcaoprovas.service.interfaces;

import java.util.List;

import com.app.correcaoprovas.model.Aluno;
import com.app.correcaoprovas.model.Nota;
import com.app.correcaoprovas.model.Prova;

public interface CalcularNotaImp {
    List<Nota> calcularNotas(List<Prova> provas, List<Aluno> alunos);
}
