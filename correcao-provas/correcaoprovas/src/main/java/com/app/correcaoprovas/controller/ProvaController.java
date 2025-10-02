package com.app.correcaoprovas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.correcaoprovas.controller.RESTFactory.AlunoRestFactory;
import com.app.correcaoprovas.controller.RESTFactory.ProvaRestFactory;
import com.app.correcaoprovas.dto.AlunoDto;
import com.app.correcaoprovas.dto.ProvaDto;
import com.app.correcaoprovas.model.Prova;
import com.app.correcaoprovas.service.AlunoService;
import com.app.correcaoprovas.service.ProvaService;
import com.app.correcaoprovas.utils.ArquivoService;
import com.app.correcaoprovas.utils.CalcularNota;
import com.app.correcaoprovas.utils.CorrecaoProva;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/prova")
public class ProvaController {

    @PostMapping()
    public ResponseEntity<String> selecionarPasta(@RequestBody String path) {        
        return ResponseEntity.ok().body(ProvaService.carregarArquivos(path).toString());
    }

    @PutMapping("/corrigir")
    public ResponseEntity<String> corrigirProva(@RequestBody String filePath) throws Exception{
        return ResponseEntity.ok(ProvaService.corrigirProvas(filePath).toString());
    }

    @PostMapping("/notas")
    public ResponseEntity<String> calcularNotas(@RequestBody Double valorQuestao) throws Exception{
        return ResponseEntity.ok().body(ProvaService.calcularNotas(valorQuestao).toString());
    }

    @GetMapping("salvar")
    public ResponseEntity<String> salvarNotas(@RequestBody String[] nomeAlunos, Double[] notas) throws Exception {
        return ResponseEntity.ok().body(ProvaService.salvarResultados(nomeAlunos, notas).toString());
    }
    
    @GetMapping("/aluno")
    public ResponseEntity<AlunoDto> getAlunoById(@RequestBody Long id){
        return ResponseEntity.ok().body(AlunoRestFactory.toDto(AlunoService.getById(id)));
    }
        
    @GetMapping("/aluno-por-turma")
    public ResponseEntity<List<AlunoDto>> getAlunoByTurma(@RequestBody String turma){
        return ResponseEntity.ok().body(AlunoRestFactory.toDto(AlunoService.getByTurma(turma)));
    }
        
    @GetMapping("/prova")
    public ResponseEntity<ProvaDto> getProvaById(@RequestBody Long id){
        return ResponseEntity.ok().body(ProvaRestFactory.toDto(ProvaService.getById(id)));
    }

    @GetMapping("/prova-por-turma")
    public ResponseEntity<List<ProvaDto>> getProvaByTurma(@RequestBody String turma){
        return ResponseEntity.ok().body(ProvaRestFactory.toDto(ProvaService.getByTurma(turma)));
    }

    @GetMapping("/ano")
    public ResponseEntity<List<ProvaDto>> getProvaByAno(@RequestBody Integer ano){
    return ResponseEntity.ok().body(ProvaRestFactory.toDto(ProvaService.getByAno(ano)));
    }
    
}
