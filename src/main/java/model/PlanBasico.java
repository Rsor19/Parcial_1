package model;

public class PlanBasico extends PlanEntrenamiento {
    /** Crea un plan básico con su tarifa mensual inicial.
     * @param id código único del plan
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
        return getValorMensual() * getDuracionMeses();
    }
}
