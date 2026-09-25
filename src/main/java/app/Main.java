package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** Punto único de entrada: úsalo también con el objetivo Maven javafx:run. */
public class Main {
    public static void main(String[] args) {
        Application.launch(SmartGymApplication.class, args);
    }

    public static class SmartGymApplication extends Application {
        @Override
        public void start(Stage stage) throws Exception {
            var location = getClass().getResource("/view/Inicio.fxml");
            if (location == null) throw new IllegalStateException("Falta el recurso /view/Inicio.fxml; verifica src/main/resources.");
            FXMLLoader loader = new FXMLLoader(location);
            stage.setTitle("Gestión de gimnasio");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
    }
}
