package com.app.correcaoprovas.service.interfaces;

import java.util.List;

import com.app.correcaoprovas.model.Prova;

public interface CorrecaoProvasImp {
    
    List<Prova> corrigirProvas(Prova prova);

    List<Prova> scannerProva();
}
