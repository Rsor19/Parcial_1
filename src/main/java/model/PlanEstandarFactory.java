package model;

public class PlanEstandarFactory extends PlanFactory{
    /**
     * Metodo sobreescrito que crea un plan Estandar
     * @param id identificador de plan
     *
     * @return
     */
    @Override
    public Plan crearPlan(String id) {
        return new PlanEstandar(id);
    }
}
