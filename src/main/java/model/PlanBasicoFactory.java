package model;


public class PlanBasicoFactory extends PlanFactory {
    /**
     * metodo sobreescrito que crea un plan básico
     * Creador concreto para instancias de PlanBasico.
     */
    @Override
    public Plan crearPlan(String id) {
        return new PlanBasico(id);
    }
}