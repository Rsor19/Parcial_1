package controller;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import java.io.IOException;

public class InicioController {
    @FXML
    private void abrirPlanes(ActionEvent event) throws IOException {
        Ventanas.abrir(event, "Planes.fxml", "Planes");
    }
    @FXML private void abrirClientes(ActionEvent e) throws IOException { Ventanas.abrir(e, "Clientes.fxml", "Clientes"); }
    @FXML private void abrirEntrenadores(ActionEvent e) throws IOException { Ventanas.abrir(e, "Entrenadores.fxml", "Entrenadores"); }
    @FXML private void abrirServicios(ActionEvent e) throws IOException { Ventanas.abrir(e, "Servicios.fxml", "Servicios adicionales"); }
    @FXML private void abrirInscripciones(ActionEvent e) throws IOException { Ventanas.abrir(e, "Inscripciones.fxml", "Inscripciones"); }
    @FXML private void abrirConsultas(ActionEvent e) throws IOException { Ventanas.abrir(e, "Consultas.fxml", "Consultas e ingresos"); }
    @FXML private void abrirPagos(ActionEvent e) throws IOException { Ventanas.abrir(e, "Pagos.fxml", "Pagos"); }
}
