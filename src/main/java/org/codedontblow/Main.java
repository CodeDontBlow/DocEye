package org.codedontblow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.codedontblow.dao.CandidatoDAO;
import org.codedontblow.gui.DatabaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Main extends Application{
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public void start(Stage stage) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/org/codedontblow/TelaInicial.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);

            Image appIcon = new Image("org/codedontblow/img/cdbLogoPreta.png");
            stage.getIcons().add(appIcon);
            stage.setResizable(false);
            stage.setTitle("DocEye");

            stage.show();
        }
        catch(Exception e){
            log.error("e: ", e);
        }
    }

    public static void main(String[] args) {
        //launch(args);
        DatabaseController databaseController = new DatabaseController();
        databaseController.buscarCandidato("ingles, html, css, java, linux, espanhol, contabilidade, administração");
    }
}
