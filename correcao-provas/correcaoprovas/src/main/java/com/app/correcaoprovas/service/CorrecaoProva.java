package com.app.correcaoprovas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.correcaoprovas.model.Prova;
import com.app.correcaoprovas.service.interfaces.CorrecaoProvasImp;

@Service
public class CorrecaoProva implements CorrecaoProvasImp {
    
    @Override
    public List<Prova> corrigirProvas(Prova prova) {
    
        return provasCorrigidas;
    }

}
