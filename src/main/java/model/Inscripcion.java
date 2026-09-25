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
    private PlanEntrenamiento planEntrenamiento;

    /** Crea una inscripción básica con código, fecha y descuento
     * @param codigoInscripcion código de la Inscripción
     * @param fechaInscripcion fecha de contratación del plan
     * @param descuentoAplicado descuento
     */
    public Inscripcion(String codigoInscripcion, LocalDate fechaInscripcion, double descuentoAplicado) {
        this.codigoInscripcion = codigoInscripcion;
        this.fechaInscripcion = fechaInscripcion;
        this.descuentoAplicado = descuentoAplicado;
        this.listaServiciosAdicionales = new ArrayList<>();
    }

    /** Crea una inscripción vinculada con el cliente, el plan, el entrenador y los servicios elegidos.
     * @param codigoInscripcion código único de la inscripción
     * @param fechaInscripcion fecha de contratación
     * @param descuentoAplicado porcentaje de descuento aplicado
     * @param cliente cliente que contrata el plan
     * @param planEntrenamiento plan contratado
     * @param entrenador entrenador asignado, si corresponde
     * @param servicios servicios adicionales incluidos
     */
    public Inscripcion(String codigoInscripcion, LocalDate fechaInscripcion, double descuentoAplicado,
                       Cliente cliente, PlanEntrenamiento planEntrenamiento, Entrenador entrenador, List<ServicioAdicional> servicios) {
        this(codigoInscripcion, fechaInscripcion, descuentoAplicado);
        this.cliente = cliente;
        this.planEntrenamiento = planEntrenamiento;
        this.entrenadorAsignado = entrenador;
        if (servicios != null) this.listaServiciosAdicionales.addAll(servicios);
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
        return planEntrenamiento;
    }

    public void setPlan(PlanEntrenamiento planEntrenamiento) {
        this.planEntrenamiento = planEntrenamiento;
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

    /** Calcula el costo del plan, sus servicios y sesiones, aplicando el descuento registrado.
     * @return valor total de la inscripción
     */
    public double calcularValorTotal() {
        if (planEntrenamiento == null) return 0;
        double total = planEntrenamiento.calcularPrecioFinal();
        for (ServicioAdicional servicio : listaServiciosAdicionales) total += servicio.getPrecio();
        if (planEntrenamiento instanceof PlanPersonalizado personalizado && entrenadorAsignado != null) {
            total += personalizado.getCantidadSesionesEntrenador() * entrenadorAsignado.getTarifaSesion();
        }
        return Math.max(0, total * (1 - descuentoAplicado / 100.0));
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
                ", plan=" + planEntrenamiento +
                '}';
    }
}
