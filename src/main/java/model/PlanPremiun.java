package model;

public class PlanPremiun extends Plan {
    /**
     * Método constructor de PlanPremium
     * @param id idenrtificador del plan
     */
    public PlanPremiun(String id) {
        super(id, 120.0);
    }

    /**
     * sobreescribe el método de calcularPrecioFinal.
     * @return 15% de descuento
     */
    @Override
    public double calcularPrecioFinal() {
        return getPrecioBase() * 0.85; // 15% de descuento
    }
}
