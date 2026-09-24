package model;

public class PlanBasico extends Plan {
    /**
     * Método constructor de PlanBasico.
     *
     * @param id identificador para el plan.
     */
    public PlanBasico(String id) {
        super(id, 50.0);
    }

    /**
     * Método sobreescrito que calcula
     * @return devuelve el precio base, no aplica descuento.
     */
    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase();
    }
}
