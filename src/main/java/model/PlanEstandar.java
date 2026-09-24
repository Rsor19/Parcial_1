package model;

public class PlanEstandar extends Plan {
    /**
     * Método constructor de Plan Estandar
     * @param id inicializa la identificacion del plan
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
        return getPrecioBase() * 0.95;
    }
}
