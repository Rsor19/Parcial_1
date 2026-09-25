package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

final class Ventanas {
    private Ventanas() { }
    static void abrir(ActionEvent event, String archivo, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(Ventanas.class.getResource("/view/" + archivo));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(titulo);
    }
}
