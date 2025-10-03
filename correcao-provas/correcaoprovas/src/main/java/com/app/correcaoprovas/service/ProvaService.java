package com.app.correcaoprovas.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.correcaoprovas.model.Prova;
import com.app.correcaoprovas.repository.ProvaRepository;
import com.app.correcaoprovas.utils.AlternativasUtils;
import com.app.correcaoprovas.utils.ProvaUtils;
import com.app.correcaoprovas.utils.GabaritoUtils;

public class ProvaService {

    @Autowired
    private static ProvaRepository provaRepo;

    public static Prova getById(Long id) {
        return provaRepo.findById(id).orElseThrow(() -> new RuntimeException("Prova não encontrada"));
    }

    public static List<Prova> getByTurma(String turma) {
       return provaRepo.findByTurma(turma).orElseThrow(() -> new RuntimeException("Turma não encontrada"));
    }

    public static List<Prova> getByAno(Integer ano) {
        return provaRepo.findByAno(ano).orElseThrow(() -> new RuntimeException("Ano não encontrada"));
    }

    public static Prova save(Prova prova) {
        ProvaUtils.infoProva().forEach((k, v) -> {
            switch (k) {
                case "turma":
                    prova.setTurma(v);
                    break;
                case "materia":
                    prova.setMateria(v);
                    break;
                case "aluno":
                    prova.setAluno_id(AlunoService.getByName(v));
                    break;
                case "ano":
                    try {
                        prova.setAno(v);
                    } catch (NumberFormatException e) {
                        System.out.println("Ano inválido: " + v);
                    }
                    break;
                default:
                    // Ignorar chaves desconhecidas
                    break;
            }
        });
        return provaRepo.save(prova);
    }

    public static Double calcularNotas(Double valorQuestao) throws Exception {
        //Num. acertos
        Integer acertos = corrigirProvas(null).values().size();
                    
        return acertos * valorQuestao;
    }

    public static Map<String, Double> salvarResultados(List<String> nomeAlunos, List<Double> notas) {
        Map<String, Double> resultados = new HashMap<>();
        for (int i = 0; i < nomeAlunos.size() && i < notas.size(); i++) {
            resultados.put(nomeAlunos.get(i), notas.get(i));
        }

        //Salvar em arquivo excel
        try {
            ProvaUtils.salvarResultadosExcel(resultados);
            JOptionPane.showMessageDialog(null, "Resultados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar resultados: " + e.getMessage(),
             "Erro", JOptionPane.ERROR_MESSAGE);
        }

        return resultados;

    }

    //Corrigir as provas
    public static Map<Integer, List<String>> corrigirProvas(String filePath) throws Exception {
        Map<Integer, List<String>> resultado = new HashMap<>();
        List<String> alternativas = AlternativasUtils.detectarAlternativas();
        List<String> gabarito = GabaritoUtils.gabaritoLista();

        //Lista de repostas comparadas com o gabrito
        if(!filePath.isEmpty()){
                alternativas.stream()
                .filter(a -> gabarito.stream().anyMatch(g -> g.equals(a)))
                .collect(Collectors.toList());
        }

        //Agrupar repostas por questão
        resultado.forEach((q, resp) -> {
            //processamento por questão
            Map.of(alternativas.indexOf(q), alternativas);
        });
        
        // //Resposta por questão
        // Map<Integer, List<String>> respostas = new HashMap<>();
        // for(String r : respostas){
        //     respostas.computeIfAbsent(prova.getQuestoes(), k -> new ArrayList<>()).add(r);
        // }

        // //Verifica cada questao
        // for (Map.Entry<Integer, List<String>> entry : porQuestao.entrySet()){
        //     int questao = entry.getKey();
        //     List<String> alternativas = entry.getValue();

        //     //Pega as marcadas
        //     Optional<String> marcada = alternativas.stream().findFirst();
                        
        //     if(marcada.isPresent()){
        //         String respostaAluno = marcada.get();
        //         String correta = gabarito.get(questao);
        //         resultado.put(questao, respostaAluno.equals(correta));
        //     } else {
        //         resultado.put(questao, false);// não marcou = errada
        //     }
        // }

        return resultado;
    }

    public static List<File> carregarArquivos(String path) {
        File pasta = new File(path);
    
        List<File> pdfs = new ArrayList<>();

        //Listar arquivos PDF na pasta
        if(pasta != null && pasta.exists() && pasta.isDirectory()){
            for (File pdf : Objects.requireNonNull(pasta.listFiles())){
                if( pdf.isFile() && pdf.getName().toLowerCase().endsWith(".pdf")){
                    pdfs.add(pdf);
                }
            }
        }

        return pdfs;
    }
}
