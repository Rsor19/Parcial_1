package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ServicioAdicional;
import model.TipoServicioAdicional;

public class ServiciosController {
    @FXML private TextField codigoField, nombreField, precioField;
    @FXML private TextArea descripcionArea;
    @FXML private ComboBox<TipoServicioAdicional> tipoCombo;
    @FXML private CheckBox disponibleCheck;
    @FXML private TableView<ServicioAdicional> tabla;
    @FXML private TableColumn<ServicioAdicional, String> codigoCol,nombreCol,descripcionCol,tipoCol;
    @FXML private TableColumn<ServicioAdicional, Number> precioCol;
    @FXML private TableColumn<ServicioAdicional, Boolean> disponibleCol;
    @FXML private void initialize(){
        tipoCombo.setItems(FXCollections.observableArrayList(TipoServicioAdicional.values())); tipoCombo.getSelectionModel().selectFirst(); disponibleCheck.setSelected(true);
        codigoCol.setCellValueFactory(new PropertyValueFactory<>("codigo"));nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));descripcionCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tipoCol.setCellValueFactory(c->new javafx.beans.property.ReadOnlyStringWrapper(c.getValue().getTipoServicio().toString()));precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));disponibleCol.setCellValueFactory(new PropertyValueFactory<>("disponible")); refrescar();
    }
    /** Registra un servicio o actualiza el seleccionado con los datos del formulario. */
    @FXML private void guardar(){try{
        if(tipoCombo.getValue()==null)throw new IllegalArgumentException("Selecciona un tipo de servicio.");
        double precio=Double.parseDouble(req(precioField,"precio"));if(precio<0)throw new IllegalArgumentException("El precio no puede ser negativo.");
        ServicioAdicional s=new ServicioAdicional(req(codigoField,"código"),req(nombreField,"nombre"),descripcionArea.getText(),precio,disponibleCheck.isSelected(),tipoCombo.getValue());
        ServicioAdicional seleccionado=tabla.getSelectionModel().getSelectedItem();if(seleccionado==null)AppContext.GIMNASIO.agregarServicio(s);else AppContext.GIMNASIO.actualizarServicio(seleccionado.getCodigo(),s);refrescar();limpiar();
    }catch(RuntimeException ex){aviso(ex.getMessage());}}
    /** Elimina de la lista el servicio seleccionado. */
    @FXML private void eliminar(){ServicioAdicional s=tabla.getSelectionModel().getSelectedItem();if(s==null){aviso("Selecciona un servicio.");return;}AppContext.GIMNASIO.eliminarServicio(s.getCodigo());refrescar();limpiar();}
    @FXML private void cargar(){ServicioAdicional s=tabla.getSelectionModel().getSelectedItem();if(s==null)return;codigoField.setText(s.getCodigo());nombreField.setText(s.getNombre());descripcionArea.setText(s.getDescripcion());precioField.setText(String.valueOf(s.getPrecio()));disponibleCheck.setSelected(s.isDisponible());tipoCombo.setValue(s.getTipoServicio());}
    @FXML private void limpiar(){codigoField.clear();nombreField.clear();descripcionArea.clear();precioField.clear();tipoCombo.getSelectionModel().selectFirst();disponibleCheck.setSelected(true);tabla.getSelectionModel().clearSelection();}
    @FXML private void volver(javafx.event.ActionEvent e)throws java.io.IOException{Ventanas.abrir(e,"Inicio.fxml","SmartGym");}
    private void refrescar(){tabla.setItems(FXCollections.observableArrayList(AppContext.GIMNASIO.getServicios()));}
    private String req(TextField f,String n){if(f.getText()==null||f.getText().isBlank())throw new IllegalArgumentException("Ingresa "+n+".");return f.getText().trim();}
    private void aviso(String s){new Alert(Alert.AlertType.WARNING,s==null?"Datos inválidos.":s).showAndWait();}
}
