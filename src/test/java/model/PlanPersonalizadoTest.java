package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

class PlanPersonalizadoTest {
    private static final Logger LOG = Logger.getLogger(PlanPersonalizadoTest.class.getName());

    /**
     * Test para que el metodocalcularPrecioFinal calcule correctamente el precio final de un plan Personalizado
     */

    @Test
    void calcularPrecioFinal() {
        LOG.info("Inicio Test Calcular Precio Final");

        PlanPersonalizado plan = new PlanPersonalizado("PLAN-1");

        double resultadoEsperado = 120.0;
        double resultadoReal = plan.calcularPrecioFinal();

        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Calcular Precio Final");
    }
}