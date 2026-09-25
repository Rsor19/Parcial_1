package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class InscripcionesController {
    @FXML private TextField codigoField, descuentoField;
    @FXML private DatePicker fechaPicker;
    @FXML private ComboBox<Cliente> clienteCombo;
    @FXML private ComboBox<PlanEntrenamiento> planCombo;
    @FXML private ComboBox<Entrenador> entrenadorCombo;
    @FXML private ListView<ServicioAdicional> serviciosList;
    @FXML private TableView<Inscripcion> tabla;
    @FXML private TableColumn<Inscripcion,String> codigoCol, clienteCol, planCol;
    @FXML private TableColumn<Inscripcion,LocalDate> fechaCol;
    @FXML private TableColumn<Inscripcion,Number> totalCol;

    @FXML private void initialize(){
        fechaPicker.setValue(LocalDate.now());
        clienteCombo.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getClientes()));
        planCombo.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getPlanes()));
        entrenadorCombo.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getEntrenadores()));
        serviciosList.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getServicios().stream()
                .filter(ServicioAdicional::isDisponible).toList()));
        serviciosList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigoInscripcion"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fechaInscripcion"));
        clienteCol.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getCliente()==null?"":c.getValue().getCliente().getNombre()));
        planCol.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getPlan()==null?"":c.getValue().getPlan().getNombre()));
        totalCol.setCellValueFactory(c -> new javafx.beans.property.ReadOnlyDoubleWrapper(c.getValue().calcularValorTotal()));
        refrescar();
    }
    /** Registra una inscripción o actualiza la seleccionada con sus relaciones y servicios. */
    @FXML private void guardar(){try{
        if(clienteCombo.getValue()==null||planCombo.getValue()==null)throw new IllegalArgumentException("Selecciona cliente y plan.");
        double descuento=descuentoField.getText().isBlank()?0:Double.parseDouble(descuentoField.getText().trim());
        if(descuento<0||descuento>100)throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        if(planCombo.getValue() instanceof PlanPersonalizado && entrenadorCombo.getValue()==null)throw new IllegalArgumentException("Asigna un entrenador al plan personalizado.");
        Inscripcion nueva=new Inscripcion(req(codigoField,"código"),fechaPicker.getValue(),descuento,clienteCombo.getValue(),planCombo.getValue(),entrenadorCombo.getValue(),new ArrayList<>(serviciosList.getSelectionModel().getSelectedItems()));
        Inscripcion seleccionada=tabla.getSelectionModel().getSelectedItem();if(seleccionada==null)AppContext.GIMNASIO.agregarInscripcion(nueva);else AppContext.GIMNASIO.actualizarInscripcion(seleccionada.getCodigoInscripcion(),nueva);
        refrescar();limpiar();
    }catch(RuntimeException ex){aviso(ex.getMessage());}}
    /** Elimina de la lista la inscripción seleccionada. */
    @FXML private void eliminar(){Inscripcion i=tabla.getSelectionModel().getSelectedItem();if(i==null){aviso("Selecciona una inscripción.");return;}AppContext.GIMNASIO.eliminarInscripcion(i.getCodigoInscripcion());refrescar();limpiar();}
    @FXML private void cargar(){Inscripcion i=tabla.getSelectionModel().getSelectedItem();if(i==null)return;codigoField.setText(i.getCodigoInscripcion());fechaPicker.setValue(i.getFechaInscripcion());descuentoField.setText(String.valueOf(i.getDescuentoAplicado()));clienteCombo.setValue(i.getCliente());planCombo.setValue(i.getPlan());entrenadorCombo.setValue(i.getEntrenadorAsignado());serviciosList.getSelectionModel().clearSelection();for(ServicioAdicional s:i.getListaServiciosAdicionales())serviciosList.getSelectionModel().select(s);}
    @FXML private void limpiar(){codigoField.clear();descuentoField.clear();fechaPicker.setValue(LocalDate.now());clienteCombo.getSelectionModel().clearSelection();planCombo.getSelectionModel().clearSelection();entrenadorCombo.getSelectionModel().clearSelection();serviciosList.getSelectionModel().clearSelection();tabla.getSelectionModel().clearSelection();}
    @FXML private void volver(javafx.event.ActionEvent e)throws java.io.IOException{Ventanas.abrir(e,"Inicio.fxml","SmartGym");}
    private String req(TextField f,String n){if(f.getText()==null||f.getText().isBlank())throw new IllegalArgumentException("Ingresa "+n+".");return f.getText().trim();}
    private void refrescar(){tabla.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getInscripciones()));}
    private void aviso(String s){new Alert(Alert.AlertType.WARNING,s==null?"Datos inválidos.":s).showAndWait();}
}
