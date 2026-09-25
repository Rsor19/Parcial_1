package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.time.LocalDate;

public class PagosController {
    @FXML private TextField codigoField, montoField;
    @FXML private DatePicker fechaPicker;
    @FXML private ComboBox<Inscripcion> inscripcionCombo;
    @FXML private ComboBox<MetodoPago> metodoCombo;
    @FXML private TableView<Pago> tabla;
    @FXML private TableColumn<Pago,String> codigoCol, inscripcionCol, metodoCol;
    @FXML private TableColumn<Pago,LocalDate> fechaCol;
    @FXML private TableColumn<Pago,Number> montoCol;

    @FXML private void initialize() {
        fechaPicker.setValue(LocalDate.now());
        inscripcionCombo.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getInscripciones()));
        metodoCombo.setItems(FXCollections.observableArrayList(MetodoPago.values())); metodoCombo.getSelectionModel().selectFirst();
        inscripcionCombo.setOnAction(e -> { Inscripcion i=inscripcionCombo.getValue(); if(i!=null)montoField.setText(String.valueOf(i.calcularValorTotal())); });
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo")); fechaCol.setCellValueFactory(new PropertyValueFactory<>("fechaPago"));
        inscripcionCol.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getInscripcion().getCodigoInscripcion()));
        metodoCol.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getMetodo().toString()));
        montoCol.setCellValueFactory(new PropertyValueFactory<>("monto")); refrescar();
    }

    /** Registra un pago o actualiza el pago seleccionado. */
    @FXML private void guardar() {
        try {
            if(inscripcionCombo.getValue()==null||metodoCombo.getValue()==null||fechaPicker.getValue()==null)throw new IllegalArgumentException("Selecciona inscripción, fecha y método de pago.");
            String codigo=req(codigoField,"código"); double monto=Double.parseDouble(req(montoField,"monto"));
            if(monto<0)throw new IllegalArgumentException("El monto no puede ser negativo.");
            Pago nuevo=new Pago(codigo,inscripcionCombo.getValue(),fechaPicker.getValue(),monto,metodoCombo.getValue());
            Pago seleccionado=tabla.getSelectionModel().getSelectedItem();
            if(seleccionado==null)AppContext.GIMNASIO.agregarPago(nuevo);else AppContext.GIMNASIO.actualizarPago(seleccionado.getCodigo(),nuevo);
            refrescar();limpiar();
        }catch(RuntimeException ex){aviso(ex.getMessage());}
    }
    /** Elimina de la lista el pago seleccionado. */
    @FXML private void eliminar(){Pago p=tabla.getSelectionModel().getSelectedItem();if(p==null){aviso("Selecciona un pago.");return;}AppContext.GIMNASIO.eliminarPago(p.getCodigo());refrescar();limpiar();}
    @FXML private void cargar(){Pago p=tabla.getSelectionModel().getSelectedItem();if(p==null)return;codigoField.setText(p.getCodigo());fechaPicker.setValue(p.getFechaPago());inscripcionCombo.setValue(p.getInscripcion());montoField.setText(String.valueOf(p.getMonto()));metodoCombo.setValue(p.getMetodo());}
    @FXML private void limpiar(){codigoField.clear();montoField.clear();fechaPicker.setValue(LocalDate.now());inscripcionCombo.getSelectionModel().clearSelection();metodoCombo.getSelectionModel().selectFirst();tabla.getSelectionModel().clearSelection();}
    @FXML private void volver(javafx.event.ActionEvent e)throws java.io.IOException{Ventanas.abrir(e,"Inicio.fxml","SmartGym");}
    private void refrescar(){tabla.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getPagos()));}
    private String req(TextField f,String n){if(f.getText()==null||f.getText().isBlank())throw new IllegalArgumentException("Ingresa "+n+".");return f.getText().trim();}
    private void aviso(String s){new Alert(Alert.AlertType.WARNING,s==null?"Datos inválidos.":s).showAndWait();}
}
