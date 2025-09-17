package com.app.correcaoprovas.model;

import java.util.List;

import org.bytedeco.opencv.opencv_core.Rect;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Prova {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "materia", nullable = false)
    private String materia;

    @Column(name = "questoes", nullable = false)
    private Integer questoes;

    @Column(name = "alternativa_marcada", nullable = false)
    private String alternativas;


    public String getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String alternativas) {
        this.alternativas = alternativas;
    }

    @Column(name = "notas", nullable = false)
    @OneToMany(mappedBy = "prova")
    private List<Nota> notas;

    public Prova(Long id, String materia, Integer questoes, String alternativas, List<Nota> notas) {
        this.id = id;
        this.materia = materia;
        this.questoes = questoes;
        this.alternativas = alternativas;
        this.notas = notas;
    }

    public Prova(int questao, String string, Rect r, Boolean marcada) {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public Integer getQuestoes() {
        return questoes;
    }

    public void setQuestoes(Integer questoes) {
        this.questoes = questoes;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
