package com.app.correcaoprovas.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import org.bytedeco.opencv.opencv_core.Rect;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.correcaoprovas.model.Prova;
import com.app.correcaoprovas.repository.ProvaRepository;
import com.app.correcaoprovas.utils.AlternativasService;
import com.app.correcaoprovas.utils.ArquivoService;
import com.app.correcaoprovas.utils.GabaritoService;

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

    public static Double calcularNotas(Double valorQuestao) {
        List<String> correcao = GabaritoService.gabaritoLista();

        //Conta acertos
        Long acertos =  correcao.stream()
                        .filter(q -> {
                            (AlternativasService.alternativasMarcadas()).filter(a -> a.equals(q))
                            .findAny().isPresent();
                        })
                        .count();
                    
        return acertos * valorQuestao;
    }

    public static Object salvarResultados(String[] nomeAlunos, Double[] notas) {
        File pastaSaida = new File("resultados");
        if(!pastaSaida.exists()) pastaSaida.mkdirs();

        JOptionPane.showMessageDialog(null, "Corrigindo provas...");

        for (File file : Objects.requireNonNull(pdfs.toArray(new File[0]))){
            if (file.getName().equalsIgnoreCase("gabarito.pdf")) continue;
            if (file.getName().toLowerCase().endsWith(".pdf")) {
                File resultados = new File(pastaSaida, file.getName().replace(".pdf", "resultado.xlsx"));// salvar em formato excel
            }

            JOptionPane.showMessageDialog(null, "Correção concluída, salvo na pasta resultados");
        }
    }

    public static Map<Integer, Boolean> corrigirProvas(String filePath) {
        Map<Integer, Boolean> resultado = new HashMap<>();
        
        //Resposta por questão
        Map<Integer, List<String>> respostas = new HashMap<>();
        for(String r : respostas){
            respostas.computeIfAbsent(prova.getQuestoes(), k -> new ArrayList<>()).add(r);
        }

        //Verifica cada questao
        for (Map.Entry<Integer, List<String>> entry : porQuestao.entrySet()){
            int questao = entry.getKey();
            List<String> alternativas = entry.getValue();

            //Pega as marcadas
            Optional<String> marcada = alternativas.stream().findFirst();
                        
            if(marcada.isPresent()){
                String respostaAluno = marcada.get();
                String correta = gabarito.get(questao);
                resultado.put(questao, respostaAluno.equals(correta));
            } else {
                resultado.put(questao, false);// não marcou = errada
            }
        }

        return respostas;
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
