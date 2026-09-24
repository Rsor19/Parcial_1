package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inscripcion {
    private String codigoInscripcion;
    private LocalDate fechaInscripcion;
    private double descuentoAplicado;
    private List<ServicioAdicional> listaServiciosAdicionales;
    private Cliente cliente;
    private Entrenador entrenadorAsignado;
    private PlanEntrenamiento plan;

    /**
     * Metodo construtor para la clase Inscripcion
     *
     * @param codigoInscripcion
     * @param fechaInscripcion
     * @param descuentoAplicado
     */
    public Inscripcion(String codigoInscripcion, LocalDate fechaInscripcion, double descuentoAplicado) {
        this.codigoInscripcion = codigoInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.descuentoAplicado = descuentoAplicado;
        this.cliente = cliente;
        this.listaServiciosAdicionales = new ArrayList<>();
        this.entrenadorAsignado = entrenadorAsignado;
        this.plan = plan;
    }

    // Getters y Setters

    public String getCodigoInscripcion() {
        return codigoInscripcion;
    }

    public void setCodigoInscripcion(String codigoInscripcion) {
        this.codigoInscripcion = codigoInscripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public PlanEntrenamiento getPlan() {
        return plan;
    }

    public void setPlan(PlanEntrenamiento plan) {
        this.plan = plan;
    }

    public Entrenador getEntrenadorAsignado() {
        return entrenadorAsignado;
    }

    public void setEntrenadorAsignado(Entrenador entrenadorAsignado) {
        this.entrenadorAsignado = entrenadorAsignado;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public List<ServicioAdicional> getListaServiciosAdicionales() {
        return listaServiciosAdicionales;
    }

    public void setListaServiciosAdicionales(List<ServicioAdicional> listaServiciosAdicionales) {
        this.listaServiciosAdicionales = listaServiciosAdicionales;
    }

    public double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    /**
     * Metodo toString de la clase Inscripcion
     * @return
     */
    @Override
    public String toString() {
        return "Inscripcion{" +
                "codigoInscripcion='" + codigoInscripcion + '\'' +
                ", fechaInscripcion=" + fechaInscripcion +
                ", descuentoAplicado=" + descuentoAplicado +
                ", listaServiciosAdicionales=" + listaServiciosAdicionales +
                ", cliente=" + cliente +
                ", entrenadorAsignado=" + entrenadorAsignado +
                ", plan=" + plan +
                '}';
    }
}
