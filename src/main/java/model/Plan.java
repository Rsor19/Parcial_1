package model;

public abstract class Plan {
    private String id;
    private double precioBase;
    private EstadoPlan estado;
    /**
     * Método constructor que incializa las variables de la clase PLan.
     * @param id El identificador único del plan.
     * @param precioBase El costo base del plan antes de aplicar posibles descuentos.
     */
    public Plan(String id, double precioBase) {
        this.id = id;
        this.precioBase = precioBase;
        this.estado = EstadoPlan.ACTIVO; // Por defecto inicia activo
    }
    /**
    Método que calcula el precio del plan
     */
    public abstract double calcularPrecioFinal();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public EstadoPlan getEstado() {
        return estado;
    }

    public void setEstado(EstadoPlan estado) {
        this.estado = estado;
    }
}
