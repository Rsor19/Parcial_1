package model;

public class PlanPremiumFactory extends PlanFactory{
    @Override
    public Plan crearPlan(String id) {
        return new PlanPremiun(id);
    }
}

