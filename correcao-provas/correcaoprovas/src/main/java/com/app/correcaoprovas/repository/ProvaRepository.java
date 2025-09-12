package com.app.correcaoprovas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.correcaoprovas.model.Prova;

@Repository
public interface ProvaRepository extends JpaRepository<Prova, Long>{
    
}
