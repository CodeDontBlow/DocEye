package org.codedontblow;

import io.github.ollama4j.exceptions.OllamaBaseException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.codedontblow.GUI.SimpleController;
import org.codedontblow.services.InputDocuments;
import org.codedontblow.services.OllamaApi;
import org.codedontblow.dao.BoletimDAO;
import org.codedontblow.dao.CandidatoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import static javafx.application.Application.launch;


public class Main extends Application{
    public void start(Stage stage) throws IOException {
        try{
            Parent root = FXMLLoader.load((getClass().getResource("TelaInicial.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);

            Image appIcon = new Image("org/codedontblow/img/cdbLogoPreta.png");
            stage.getIcons().add(appIcon);
            stage.setResizable(false);
            stage.setTitle("DocEye");

            stage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws OllamaBaseException, IOException, InterruptedException, SQLException {
//        System.out.println("Hello CodeDontBlow!");
//        InputDocuments i1 = new InputDocuments();
//        OllamaApi api = new OllamaApi();
//        CandidatoDAO candidatos = new CandidatoDAO();
//        BoletimDAO boletins = new BoletimDAO();
//
//
//
//        String filePath = i1.selectFile();
//        String output = api.processFile(filePath);
//
//        candidatos.ler();
//        boletins.ler();
        launch(args);
    }
}
