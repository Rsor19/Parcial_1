package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

class PlanBasicoTest {
    private static final Logger LOG = Logger.getLogger(PlanBasicoTest.class.getName());

    /**
     * Test para que el metodocalcularPrecioFinal calcule correctamente el precio final de un plan basico
     */
    @Test
    void calcularPrecioFinal() {
        LOG.info("Inicio Test Calcular Precio Final");

        PlanBasico plan = new PlanBasico("PLAN-1");
        double resultadoEsperado = 50.0;
        double resultadoReal = plan.calcularPrecioFinal();
        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Calcular Precio Final");
    }
}