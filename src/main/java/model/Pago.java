package model;

import java.time.LocalDate;

public class Pago {
    private String codigo;
    private Inscripcion inscripcion;
    private LocalDate fechaPago;
    private double monto;
    private MetodoPago metodo;

    /** Crea un pago asociado a una inscripción.
     * @param codigo identificador del pago
     * @param inscripcion inscripción que se está pagando
     * @param fechaPago fecha en que se recibió el pago
     * @param monto cantidad recibida
     * @param metodo forma de pago utilizada
     */
    public Pago(String codigo, Inscripcion inscripcion, LocalDate fechaPago, double monto, MetodoPago metodo) {
        this.codigo = codigo;
        this.inscripcion = inscripcion;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.metodo = metodo;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Inscripcion getInscripcion() { return inscripcion; }
    public void setInscripcion(Inscripcion inscripcion) { this.inscripcion = inscripcion; }
    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public MetodoPago getMetodo() { return metodo; }
    public void setMetodo(MetodoPago metodo) { this.metodo = metodo; }
    @Override public String toString() { return codigo + " - " + monto; }
}
