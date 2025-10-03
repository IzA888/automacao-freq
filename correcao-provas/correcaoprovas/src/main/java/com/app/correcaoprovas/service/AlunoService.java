package com.app.correcaoprovas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.correcaoprovas.model.Aluno;
import com.app.correcaoprovas.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private static AlunoRepository alunoRepo;

    public static Aluno getById(Long id) {
        return alunoRepo.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public static List<Aluno> getByTurma(String turma) {
        return alunoRepo.findByTurma(turma).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    public static Aluno getByName(String nome) {
        return alunoRepo.findByName(nome).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

}
