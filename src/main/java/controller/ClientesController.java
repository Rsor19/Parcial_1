package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;

import java.time.LocalDate;

public class ClientesController {
    @FXML private TextField documentoField, nombreField, telefonoField, correoField, edadField;
    @FXML private DatePicker fechaField;
    @FXML private TableView<Cliente> tabla;
    @FXML private TableColumn<Cliente, String> documentoCol, nombreCol, telefonoCol, correoCol;
    @FXML private TableColumn<Cliente, Number> edadCol;
    @FXML private TableColumn<Cliente, LocalDate> fechaCol;

    @FXML private void initialize() {
        fechaField.setValue(LocalDate.now());
        documentoCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        telefonoCol.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        correoCol.setCellValueFactory(new PropertyValueFactory<>("correo"));
        edadCol.setCellValueFactory(new PropertyValueFactory<>("edad"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));
        refrescar();
    }
    /** Registra un cliente o actualiza el seleccionado con los datos del formulario. */
    @FXML private void guardar() {
        try {
            String documento = requerido(documentoField.getText(), "documento");
            int edad = Integer.parseInt(requerido(edadField.getText(), "edad"));
            if (edad < 1 || telefonoField.getText().isBlank()) throw new IllegalArgumentException("Revisa la edad y el teléfono.");
            Cliente c = new Cliente(documento, requerido(nombreField.getText(), "nombre"), telefonoField.getText().trim(),
                    requerido(correoField.getText(), "correo"), edad, fechaField.getValue());
            Cliente seleccionado = tabla.getSelectionModel().getSelectedItem();
            if (seleccionado == null) AppContext.GIMNASIO.agregarCliente(c);
            else AppContext.GIMNASIO.actualizarCliente(seleccionado.getId(), c);
            refrescar(); limpiar();
        } catch (RuntimeException ex) { aviso(ex.getMessage()); }
    }
    /** Elimina de la lista al cliente seleccionado. */
    @FXML private void eliminar() {
        Cliente c = tabla.getSelectionModel().getSelectedItem();
        if (c == null) { aviso("Selecciona un cliente."); return; }
        AppContext.GIMNASIO.eliminarCliente(c.getId()); refrescar(); limpiar();
    }
    @FXML private void cargar() {
        Cliente c = tabla.getSelectionModel().getSelectedItem(); if (c == null) return;
        documentoField.setText(c.getId()); nombreField.setText(c.getNombre()); telefonoField.setText(c.getTelefono());
        correoField.setText(c.getCorreo()); edadField.setText(String.valueOf(c.getEdad())); fechaField.setValue(c.getFechaRegistro());
    }
    @FXML private void limpiar() {
        documentoField.clear(); nombreField.clear(); telefonoField.clear(); correoField.clear(); edadField.clear();
        fechaField.setValue(LocalDate.now()); tabla.getSelectionModel().clearSelection();
    }
    @FXML private void volver(javafx.event.ActionEvent e) throws java.io.IOException { Ventanas.abrir(e, "Inicio.fxml", "SmartGym"); }
    private void refrescar() { tabla.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getClientes())); }
    private String requerido(String s, String campo) { if (s == null || s.isBlank()) throw new IllegalArgumentException("Ingresa " + campo + "."); return s.trim(); }
    private void aviso(String texto) { new Alert(Alert.AlertType.WARNING, texto == null ? "Datos no válidos." : texto).showAndWait(); }
}
