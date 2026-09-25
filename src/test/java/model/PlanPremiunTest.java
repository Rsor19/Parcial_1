package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

class PlanPremiunTest {
    private static final Logger LOG = Logger.getLogger(PlanPremiunTest.class.getName());

    /**
     * Test para que el metodocalcularPrecioFinal calcule correctamente el precio final de un plan premium
     */

    @Test
    void calcularPrecioFinal() {
        LOG.info("Inicio Test Calcular Precio Final");

        PlanPremiun plan = new PlanPremiun("PLAN-1");

        double resultadoEsperado = 102.0;
        double resultadoReal = plan.calcularPrecioFinal();

        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Calcular Precio Final");
    }
}