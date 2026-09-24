package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona{
    private String edad;
    private LocalDate fechaRegistro;
    private List<Inscripcion> listaInscripciones;

    /**
     * Metodo constructor de la clase Cliente
     * @param id
     * @param nombre
     * @param telefono
     * @param correo
     * @param edad
     * @param fechaRegistro
     * @param listaInscripciones
     */

    public Cliente(String id, String nombre, String telefono, String correo, String edad, LocalDate fechaRegistro, List<Inscripcion> listaInscripciones) {
        super(id, nombre, telefono, correo);
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
        this.listaInscripciones = new ArrayList<>();
    }

    //Getters y Setters


    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Inscripcion> getListaInscripciones() {
        return listaInscripciones;
    }

    public void setListaInscripciones(List<Inscripcion> listaInscripciones) {
        this.listaInscripciones = listaInscripciones;
    }

    /**
     * Metodo ToString de la clase Cliente
     * @return
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "edad='" + edad + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", listaInscripciones=" + listaInscripciones +
                '}';
    }

    /**
     * Sobreescritura del metodo validarIdentidad para la clase Cliente
     * @return
     */
    @Override
    public String validarIdentidad() {
        return "";
    }

}
