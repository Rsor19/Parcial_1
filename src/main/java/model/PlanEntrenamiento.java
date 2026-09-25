package model;

import java.util.ArrayList;
import java.util.List;

public abstract class PlanEntrenamiento {
    private String id;
    private String nombre;
    private String descripcion;
    private int duracionMeses;
    private double valorMensual;
    private EstadoPlan estado;
    private final List<String> beneficios = new ArrayList<>();

    /** Crea un plan con nombre y duración predeterminados a partir de su código y valor mensual.
     * @param id código único del plan
     * @param precioBase valor mensual inicial
     */
    protected PlanEntrenamiento(String id, double precioBase) {
        this(id, "Plan " + id, "", 1, precioBase, EstadoPlan.ACTIVO, List.of());
    }

    /** Crea un plan con sus datos comerciales completos.
     * @param id código único del plan
     * @param nombre nombre que verá el cliente
     * @param descripcion descripción del plan
     * @param duracionMeses duración contratada en meses
     * @param valorMensual valor mensual del plan
     * @param estado estado inicial del plan
     * @param beneficios beneficios incluidos
     */
    protected PlanEntrenamiento(String id, String nombre, String descripcion, int duracionMeses,
                                double valorMensual, EstadoPlan estado, List<String> beneficios) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado == null ? EstadoPlan.ACTIVO : estado;
        if (beneficios != null) this.beneficios.addAll(beneficios);
    }

    public abstract double calcularPrecioFinal();
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCodigo() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getDuracionMeses() { return duracionMeses; }
    public void setDuracionMeses(int duracionMeses) { this.duracionMeses = duracionMeses; }
    public double getValorMensual() { return valorMensual; }
    public void setValorMensual(double valorMensual) { this.valorMensual = valorMensual; }
    public double getPrecioBase() { return valorMensual; }
    public void setPrecioBase(double precioBase) { this.valorMensual = precioBase; }
    public EstadoPlan getEstado() { return estado; }
    public void setEstado(EstadoPlan estado) { this.estado = estado; }
    public List<String> getBeneficios() { return List.copyOf(beneficios); }
    public void setBeneficios(List<String> valores) { beneficios.clear(); if (valores != null) beneficios.addAll(valores); }
    @Override public String toString() { return id + " - " + nombre; }
}
