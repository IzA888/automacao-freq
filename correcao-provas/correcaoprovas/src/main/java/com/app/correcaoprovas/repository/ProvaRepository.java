package com.app.correcaoprovas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.correcaoprovas.model.Prova;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long>{

    Optional<List<Prova>> findByTurma(String turma);

    Optional<List<Prova>> findByAno(Integer ano);
    
}
