// package com.app.correcaoprovas.GUI;

// import java.io.File;

// // import javafx.scene.layout.Pane;
// // import javafx.scene.control.ScrollPane;
// import javafx.stage.Stage;

// import java.awt.*;

// import com.app.correcaoprovas.service.ArquivoService;
// import org.open
// // import javafx.scene.Scene;
// // import javafx.scene.layout.VBox;
// // import javafx.stage.DirectoryChooser;

// public class ProvasGUI extends Scene {
//     private Button btnSelecionarPasta;
//     private Button btnProcessar;
//     private TextArea logArea;
//     private File pastaSelecionada;

//     public ProvasGUI() {
//         setTitle("Correção de provas");

//         btnSelecionarPasta = new Button("Selecionar pasta");
//         btnProcessar = new Button("Processar provas");
//         logArea = new TextArea();
//         logArea.setEditable(false);

//         VBox panel = new VBox(10, btnSelecionarPasta, btnProcessar);
//         ScrollPane scrollPane = new ScrollPane(logArea);
//         VBox root = new VBox(10, panel, scrollPane);

//         btnSelecionarPasta.setOnAction(e -> {
//             DirectoryChooser chooser = new DirectoryChooser();
//             chooser.setTitle("Selecionar pasta");
//             File pasta = chooser.showDialog(this);
//             if (pasta != null) {
//                 pastaSelecionada = pasta;
//                 log("Pasta selecionada: " + pastaSelecionada.getAbsolutePath());
//             }
//         });

//         btnProcessar.setOnAction(e -> {
//             var pdfs = ArquivoService.extrairPdfs();
//             if (pdfs != null) {
//                 pdfs.stream().forEach(pdf -> log("Processando pasta selecionada: " + pdf));
//             } else {
//                 log("Erro ao processar arquivos.");
//             }
//             log("Processamento Concluído");
//         });

//         setScene(new Scene(root, 600, 400));
//     }

//     private void log(String msg) {
//         logArea.appendText(msg + "\n");
//     }
// }
