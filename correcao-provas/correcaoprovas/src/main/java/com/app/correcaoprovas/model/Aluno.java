package com.app.correcaoprovas.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Aluno {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "turma")
    private String turma;

    @Column(name = "ano")
    private String ano;
    
    @Column(name = "notas")
    private Double notas;

    @OneToOne(optional = false, mappedBy = "prova")
    private Prova prova_id;

    public Aluno(Long id, String nome, String turma, String ano, Double notas, Prova prova_id) {
        this.id = id;
        this.nome = nome;
        this.turma = turma;
        this.ano = ano;
        this.notas = notas;
        this.prova_id = prova_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Double getNotas() {
        return notas;
    }

    public void setNotas(Double notas) {
        this.notas = notas;
    }

    public Prova getProva_id() {
        return prova_id;
    }

    public void setProva_id(Prova prova_id) {
        this.prova_id = prova_id;
    }

}
