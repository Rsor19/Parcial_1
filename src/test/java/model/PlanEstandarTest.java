package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

class PlanEstandarTest {
    private static final Logger LOG = Logger.getLogger(PlanEstandarTest.class.getName());

    /**
     * Test para que el metodocalcularPrecioFinal calcule correctamente el precio final de un plan Estandar
     */
    @Test
    void calcularPrecioFinal() {
        LOG.info("Inicio Test Calcular Precio Final");

        PlanEstandar plan = new PlanEstandar("PLAN-1");

        double resultadoEsperado = 76.0;
        double resultadoReal = plan.calcularPrecioFinal();

        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Calcular Precio Final");
    }
}