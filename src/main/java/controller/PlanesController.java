package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.EstadoPlan;
import model.Gimnasio;
import model.Plan;
import model.PlanBasicoFactory;
import model.PlanEstandarFactory;
import model.PlanFactory;
import model.PlanPremiumFactory;

import java.io.IOException;

public class PlanesController {
    private static final Gimnasio GIMNASIO = new Gimnasio();

    @FXML private TextField idField;
    @FXML private ComboBox<String> tipoCombo;
    @FXML private TableView<Plan> planesTable;
    @FXML private TableColumn<Plan, String> idColumn;
    @FXML private TableColumn<Plan, String> tipoColumn;
    @FXML private TableColumn<Plan, Number> precioColumn;
    @FXML private TableColumn<Plan, Number> precioFinalColumn;
    @FXML private TableColumn<Plan, EstadoPlan> estadoColumn;

    @FXML
    private void initialize() {
        tipoCombo.setItems(FXCollections.observableArrayList("BASICO", "ESTANDAR", "PREMIUM"));
        tipoCombo.getSelectionModel().selectFirst();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tipoColumn.setCellValueFactory(cell -> new javafx.beans.property.ReadOnlyStringWrapper(
                nombreTipo(cell.getValue())));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precioBase"));
        precioFinalColumn.setCellValueFactory(cell -> new javafx.beans.property.ReadOnlyDoubleWrapper(
                cell.getValue().calcularPrecioFinal()));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        refrescarTabla();
    }

    @FXML
    private void agregarPlan() {
        String id = idField.getText() == null ? "" : idField.getText().trim();
        if (id.isEmpty() || tipoCombo.getValue() == null) {
            mostrar("Datos incompletos", "Ingresa un identificador y selecciona un tipo de plan.", Alert.AlertType.WARNING);
            return;
        }
        try {
            GIMNASIO.agregarPlan(fabricaSeleccionada().crearPlan(id));
            limpiarFormulario();
            refrescarTabla();
        } catch (IllegalArgumentException ex) {
            mostrar("No se pudo agregar", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cambiarEstado() {
        Plan seleccionado = planesTable.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrar("Selecciona un plan", "Selecciona una fila para cambiar su estado.", Alert.AlertType.INFORMATION);
            return;
        }
        seleccionado.setEstado(seleccionado.getEstado() == EstadoPlan.ACTIVO
                ? EstadoPlan.INACTIVO : EstadoPlan.ACTIVO);
        refrescarTabla();
        planesTable.getSelectionModel().select(seleccionado);
    }

    @FXML
    private void actualizarPlan() {
        Plan seleccionado = planesTable.getSelectionModel().getSelectedItem();
        String id = idField.getText() == null ? "" : idField.getText().trim();
        if (seleccionado == null) {
            mostrar("Selecciona un plan", "Selecciona una fila antes de actualizar.", Alert.AlertType.INFORMATION);
            return;
        }
        if (id.isEmpty() || tipoCombo.getValue() == null) {
            mostrar("Datos incompletos", "Ingresa un identificador y selecciona un tipo de plan.", Alert.AlertType.WARNING);
            return;
        }
        try {
            Plan actualizado = fabricaSeleccionada().crearPlan(id);
            actualizado.setEstado(seleccionado.getEstado());
            GIMNASIO.actualizarPlan(seleccionado.getId(), actualizado);
            refrescarTabla();
            planesTable.getSelectionModel().select(actualizado);
        } catch (IllegalArgumentException ex) {
            mostrar("No se pudo actualizar", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void eliminarPlan() {
        Plan seleccionado = planesTable.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrar("Selecciona un plan", "Selecciona una fila para eliminarla.", Alert.AlertType.INFORMATION);
            return;
        }
        GIMNASIO.eliminarPlan(seleccionado.getId());
        refrescarTabla();
        limpiarFormulario();
    }

    @FXML
    private void cargarSeleccion() {
        Plan seleccionado = planesTable.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;
        idField.setText(seleccionado.getId());
        tipoCombo.setValue(nombreTipo(seleccionado));
    }

    @FXML
    private void limpiarFormulario() {
        idField.clear();
        tipoCombo.getSelectionModel().selectFirst();
        planesTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Inicio.fxml"));
        Scene scene = new Scene(loader.load());
        var stage = (javafx.stage.Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Gestión de gimnasio");
    }

    private PlanFactory fabricaSeleccionada() {
        return switch (tipoCombo.getValue()) {
            case "BASICO" -> new PlanBasicoFactory();
            case "ESTANDAR" -> new PlanEstandarFactory();
            case "PREMIUM" -> new PlanPremiumFactory();
            default -> throw new IllegalStateException("Tipo de plan no válido");
        };
    }

    private String nombreTipo(Plan plan) {
        return switch (plan.getClass().getSimpleName()) {
            case "PlanBasico" -> "BASICO";
            case "PlanEstandar" -> "ESTANDAR";
            default -> "PREMIUM";
        };
    }

    private void refrescarTabla() {
        planesTable.setItems(FXCollections.observableArrayList(GIMNASIO.getPlanes()));
    }

    private void mostrar(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
