package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Cliente;

import java.time.LocalDate;

public class ConsultasController {
    @FXML private TextField telefonoField;
    @FXML private DatePicker desdePicker, hastaPicker;
    @FXML private Label resultadoCliente, resultadoIngresos, resultadoPagos;

    @FXML private void initialize() { desdePicker.setValue(LocalDate.now().withDayOfMonth(1)); hastaPicker.setValue(LocalDate.now()); }
    @FXML private void buscarCliente() {
        Cliente cliente=AppContext.GIMNASIO.buscarClientePorTelefono(telefonoField.getText().trim());
        if(cliente==null){resultadoCliente.setText("No se encontró un cliente con ese teléfono.");return;}
        boolean perfecto=AppContext.GIMNASIO.validarTelefonoNumeroPerfecto(cliente);
        resultadoCliente.setText("Cliente: "+cliente.getNombre()+" | Documento: "+cliente.getId()+" | Teléfono: "+cliente.getTelefono()+" | Número perfecto: "+(perfecto?"Sí":"No"));
    }
    @FXML private void calcularIngresos(){try{
        double total=AppContext.GIMNASIO.calcularIngresos(desdePicker.getValue(),hastaPicker.getValue());
        resultadoIngresos.setText(String.format("Ingresos del %s al %s: $%.2f",desdePicker.getValue(),hastaPicker.getValue(),total));
        double pagos=AppContext.GIMNASIO.calcularPagos(desdePicker.getValue(),hastaPicker.getValue());
        resultadoPagos.setText(String.format("Pagos registrados en el periodo: $%.2f",pagos));
    }catch(RuntimeException e){new Alert(Alert.AlertType.WARNING,e.getMessage()).showAndWait();}}
    @FXML private void volver(javafx.event.ActionEvent e)throws java.io.IOException{Ventanas.abrir(e,"Inicio.fxml","SmartGym");}
}
