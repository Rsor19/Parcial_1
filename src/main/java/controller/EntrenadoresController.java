package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Entrenador;
import model.EspecialidadEntrenador;

public class EntrenadoresController {
    @FXML private TextField idField, nombreField, telefonoField, correoField, tarifaField;
    @FXML private ComboBox<EspecialidadEntrenador> especialidadCombo;
    @FXML private TableView<Entrenador> tabla;
    @FXML private TableColumn<Entrenador, String> idCol, nombreCol, telefonoCol, correoCol, especialidadCol;
    @FXML private TableColumn<Entrenador, Number> tarifaCol;
    @FXML private void initialize() {
        especialidadCombo.setItems(FXCollections.observableArrayList(EspecialidadEntrenador.values()));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id")); nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<>("telefono")); correoCol.setCellValueFactory(new PropertyValueFactory<>("correo"));
        especialidadCol.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getEspecialidad().toString()));
        tarifaCol.setCellValueFactory(new PropertyValueFactory<>("tarifaSesion")); refrescar();
    }
    /** Registra un entrenador o actualiza el seleccionado con los datos del formulario. */
    @FXML private void guardar() {
        try {
            Entrenador nuevo = new Entrenador(req(idField,"identificación"), req(nombreField,"nombre"), req(telefonoField,"teléfono"), req(correoField,"correo"),
                    especialidadCombo.getValue(), Double.parseDouble(req(tarifaField,"tarifa")));
            if (nuevo.getEspecialidad() == null || nuevo.getTarifaSesion() < 0) throw new IllegalArgumentException("Selecciona especialidad e ingresa tarifa válida.");
            Entrenador seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) AppContext.GIMNASIO.agregarEntrenador(nuevo);
            else AppContext.GIMNASIO.actualizarEntrenador(seleccionado.getId(), nuevo);
            refrescar(); limpiar();
        } catch (RuntimeException ex) { aviso(ex.getMessage()); }
    }
    /** Elimina de la lista al entrenador seleccionado. */
    @FXML private void eliminar() { Entrenador e=tabla.getSelectionModel().getSelectedItem(); if(e==null){aviso("Selecciona un entrenador.");return;} AppContext.GIMNASIO.eliminarEntrenador(e.getId());refrescar();limpiar(); }
    @FXML private void cargar() { Entrenador e=tabla.getSelectionModel().getSelectedItem();if(e==null)return; idField.setText(e.getId());nombreField.setText(e.getNombre());telefonoField.setText(e.getTelefono());correoField.setText(e.getCorreo());tarifaField.setText(String.valueOf(e.getTarifaSesion()));especialidadCombo.setValue(e.getEspecialidad()); }
    @FXML private void limpiar() { idField.clear();nombreField.clear();telefonoField.clear();correoField.clear();tarifaField.clear();especialidadCombo.getSelectionModel().clearSelection();tabla.getSelectionModel().clearSelection(); }
    @FXML private void volver(javafx.event.ActionEvent e) throws java.io.IOException { Ventanas.abrir(e,"Inicio.fxml","SmartGym"); }
    private void refrescar(){tabla.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getEntrenadores()));}
    private String req(TextField f,String n){if(f.getText()==null||f.getText().isBlank())throw new IllegalArgumentException("Ingresa "+n+".");return f.getText().trim();}
    private void aviso(String s){new Alert(Alert.AlertType.WARNING,s==null?"Datos inválidos.":s).showAndWait();}
}
