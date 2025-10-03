package com.app.correcaoprovas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.correcaoprovas.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<List<Aluno>> findByTurma(String turma);

    Optional<Aluno> findByNome(String nome);
    
}
