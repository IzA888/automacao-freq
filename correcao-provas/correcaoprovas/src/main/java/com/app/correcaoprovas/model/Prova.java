package com.app.correcaoprovas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Prova {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "materia", nullable = false)
    private String materia;

    @Column(name = "turma")
    private String turma;

    @Column(name = "ano")
    private String ano;

    @Column(name = "questoes", nullable = false)
    private Integer questoes;

    @Column(name = "alternativa_marcada", nullable = false)
    private String alternativas;

    @OneToOne(optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno_id;

    public Prova(Long id, String materia, String turma, String ano, Integer questoes, String alternativas,
            Aluno aluno_id) {
        this.id = id;
        this.materia = materia;
        this.turma = turma;
        this.ano = ano;
        this.questoes = questoes;
        this.alternativas = alternativas;
        this.aluno_id = aluno_id;
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

    public Integer getQuestoes() {
        return questoes;
    }

    public void setQuestoes(Integer questoes) {
        this.questoes = questoes;
    }

    public String getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(String alternativas) {
        this.alternativas = alternativas;
    }

    public Aluno getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(Aluno aluno_id) {
        this.aluno_id = aluno_id;
    }


}
