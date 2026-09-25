package model;

public class PlanPremiun extends PlanEntrenamiento {
    /** Crea un plan premium con su tarifa mensual inicial.
     * @param id código único del plan
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
        return getValorMensual() * getDuracionMeses() * 0.85; // 15% de descuento
    }
}
