package com.app.correcaoprovas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.correcaoprovas.model.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
    
}
