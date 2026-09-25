package model;

public class PlanPremiumFactory extends PlanFactory{
    @Override
    public PlanEntrenamiento crearPlan(String id) {
        return new PlanPremiun(id);
    }
}

