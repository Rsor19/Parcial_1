package model;

public class PlanPersonalizadoFactory extends PlanFactory {
    @Override
    public PlanEntrenamiento crearPlan(String id) {
        return new PlanPersonalizado(id);
    }
}
