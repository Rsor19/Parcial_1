package model;

import java.time.LocalDate;
import java.util.List;

public class Cliente extends Persona {
    private int edad;
    private LocalDate fechaRegistro;

    /** Crea el perfil de un cliente con sus datos de contacto y registro.
     * @param id documento de identidad
     * @param nombre nombre completo
     * @param telefono número de teléfono
     * @param correo correo electrónico
     * @param edad edad del cliente
     * @param fechaRegistro fecha en que se registró
     */
    public Cliente(String id, String nombre, String telefono, String correo, int edad, LocalDate fechaRegistro) {
        super(id, nombre, telefono, correo);
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String validarIdentidad(List<Persona> personas) {
        return personas.stream().anyMatch(p -> p != this && getId().equals(p.getId()))
                ? "Documento ya registrado" : "Documento disponible";
    }

    @Override
    public String toString() { return getNombre() + " (" + getTelefono() + ")"; }
}
