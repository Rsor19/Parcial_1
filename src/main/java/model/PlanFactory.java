package model;

public abstract class PlanFactory {
    /**
     * Metodo que crea un objeto de tipo Plan
     * @param id identificador de plan
     *
     * @return
     */
    public abstract Plan crearPlan(String id);
}
