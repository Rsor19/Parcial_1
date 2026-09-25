package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.util.Arrays;

public class PlanesController {
    @FXML private TextField codigoField, nombreField, duracionField, valorMensualField, beneficiosField;
    @FXML private TextArea descripcionArea, objetivosArea;
    @FXML private ComboBox<String> tipoCombo;
    @FXML private ComboBox<EstadoPlan> estadoCombo;
    @FXML private ComboBox<EspecialidadEntrenador> especialidadCombo;
    @FXML private TextField sesionesField;
    @FXML private TableView<PlanEntrenamiento> planesTable;
    @FXML private TableColumn<PlanEntrenamiento, String> codigoColumn, nombreColumn, tipoColumn;
    @FXML private TableColumn<PlanEntrenamiento, Number> duracionColumn, valorColumn, totalColumn;
    @FXML private TableColumn<PlanEntrenamiento, EstadoPlan> estadoColumn;

    @FXML private void initialize() {
        tipoCombo.setItems(FXCollections.observableArrayList("BASICO", "PREMIUM", "PERSONALIZADO"));
        tipoCombo.getSelectionModel().selectFirst();
        estadoCombo.setItems(FXCollections.observableArrayList(EstadoPlan.values()));
        estadoCombo.getSelectionModel().selectFirst();
        especialidadCombo.setItems(FXCollections.observableArrayList(EspecialidadEntrenador.values()));
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tipoColumn.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyStringWrapper(tipo(c.getValue())));
        duracionColumn.setCellValueFactory(new PropertyValueFactory<>("duracionMeses"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));
        totalColumn.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyDoubleWrapper(c.getValue().calcularPrecioFinal()));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        refrescar();
    }

    /** Crea un plan con la fábrica elegida o actualiza el plan seleccionado. */
    @FXML private void guardar() {
        try {
            PlanEntrenamiento planEntrenamiento = crearPlan();
            PlanEntrenamiento seleccionado = planesTable.getSelectionModel().getSelectedItem();
            if (seleccionado == null) AppContext.GIMNASIO.agregarPlan(planEntrenamiento);
            else AppContext.GIMNASIO.actualizarPlan(seleccionado.getId(), planEntrenamiento);
            limpiar(); refrescar();
        } catch (RuntimeException ex) { aviso(ex.getMessage()); }
    }

    private PlanEntrenamiento crearPlan() {
        String codigo = requerido(codigoField.getText(), "código");
        String nombre = requerido(nombreField.getText(), "nombre");
        int meses = Integer.parseInt(requerido(duracionField.getText(), "duración"));
        double mensual = Double.parseDouble(requerido(valorMensualField.getText(), "valor mensual"));
        if (meses < 1 || mensual < 0) throw new IllegalArgumentException("Duración y valor deben ser válidos.");
        PlanFactory factory = switch (tipoCombo.getValue()) {
            case "BASICO" -> new PlanBasicoFactory();
            case "PREMIUM" -> new PlanPremiumFactory();
            case "PERSONALIZADO" -> new PlanPersonalizadoFactory();
            default -> throw new IllegalArgumentException("Selecciona el tipo de plan.");
        };
        PlanEntrenamiento planEntrenamiento = factory.crearPlan(codigo);
        planEntrenamiento.setNombre(nombre);
        planEntrenamiento.setDescripcion(descripcionArea.getText());
        planEntrenamiento.setDuracionMeses(meses);
        planEntrenamiento.setValorMensual(mensual);
        planEntrenamiento.setEstado(estadoCombo.getValue());
        planEntrenamiento.setBeneficios(Arrays.stream(beneficiosField.getText().split(",")).map(String::trim).filter(s -> !s.isEmpty()).toList());
        if (planEntrenamiento instanceof PlanPersonalizado p) {
            int sesiones = Integer.parseInt(requerido(sesionesField.getText(), "sesiones"));
            if (sesiones < 1 || especialidadCombo.getValue() == null || objetivosArea.getText().isBlank())
                throw new IllegalArgumentException("El plan personalizado requiere sesiones, especialidad y objetivos.");
            p.setCantidadSesionesEntrenador(sesiones);
            p.setEspecialidadRequerida(especialidadCombo.getValue());
            p.setObjetivosCliente(objetivosArea.getText().trim());
        }
        return planEntrenamiento;
    }

    /** Elimina de la lista el plan seleccionado. */
    @FXML private void eliminar() {
        PlanEntrenamiento planEntrenamiento = planesTable.getSelectionModel().getSelectedItem();
        if (planEntrenamiento == null) { aviso("Selecciona un plan."); return; }
        AppContext.GIMNASIO.eliminarPlan(planEntrenamiento.getId()); refrescar(); limpiar();
    }

    @FXML private void cargarSeleccion() {
        PlanEntrenamiento p = planesTable.getSelectionModel().getSelectedItem(); if (p == null) return;
        codigoField.setText(p.getId()); nombreField.setText(p.getNombre()); descripcionArea.setText(p.getDescripcion());
        tipoCombo.setValue(tipo(p)); duracionField.setText(String.valueOf(p.getDuracionMeses()));
        valorMensualField.setText(String.valueOf(p.getValorMensual())); estadoCombo.setValue(p.getEstado());
        beneficiosField.setText(String.join(", ", p.getBeneficios()));
        if (p instanceof PlanPersonalizado personal) {
            sesionesField.setText(String.valueOf(personal.getCantidadSesionesEntrenador()));
            especialidadCombo.setValue(personal.getEspecialidadRequerida()); objetivosArea.setText(personal.getObjetivosCliente());
        }
    }

    @FXML private void limpiar() {
        codigoField.clear(); nombreField.clear(); descripcionArea.clear(); duracionField.clear(); valorMensualField.clear();
        beneficiosField.clear(); sesionesField.clear(); objetivosArea.clear(); planesTable.getSelectionModel().clearSelection();
    }

    @FXML private void volver(javafx.event.ActionEvent e) throws java.io.IOException { Ventanas.abrir(e, "Inicio.fxml", "SmartGym"); }
    private void refrescar() { planesTable.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getPlanes())); }
    private String requerido(String s, String nombre) { if (s == null || s.isBlank()) throw new IllegalArgumentException("Ingresa " + nombre + "."); return s.trim(); }
    private String tipo(PlanEntrenamiento p) { return p instanceof PlanPersonalizado ? "PERSONALIZADO" : p instanceof PlanPremiun ? "PREMIUM" : "BASICO"; }
    private void aviso(String texto) { Alert a = new Alert(Alert.AlertType.WARNING, texto); a.showAndWait(); }
}
