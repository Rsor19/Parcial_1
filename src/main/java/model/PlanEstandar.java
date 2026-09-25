package model;

public class PlanEstandar extends PlanEntrenamiento {
    /** Crea un plan estándar con su tarifa mensual inicial.
     * @param id código único del plan
     */
    public PlanEstandar(String id) {
        super(id, 80.0);
    }

    /**
     * metodo constructor sobreescrito que calcula el precio final y añade un 5 por ciento de desuento
     * @return descuento del 5 por ciento
     */
    @Override
    public double calcularPrecioFinal() {
        return getValorMensual() * getDuracionMeses() * 0.95;
    }
}
