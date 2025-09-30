package com.app.correcaoprovas.dto;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AlunoDto {
 
    private String nome;
    private String turma;
    private String ano;
    private Double notas;
}
