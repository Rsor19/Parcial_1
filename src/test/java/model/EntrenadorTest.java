package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class EntrenadorTest {
    private static final Logger LOG = Logger.getLogger(EntrenadorTest.class.getName());

    /**
     * Tests para verificar que el metodo validarIdentidad funcione correctamente
     */
    @Test
    void validarIdentidad() {
        LOG.info("Inicio Test Validar Identidad");

        Entrenador entrenador = new Entrenador("E-1", "Carlos Ruiz", "3101234567", "carlos@correo.com", EspecialidadEntrenador.MUSCULACION, 50000);
        List<Persona> personas = new ArrayList<>();
        personas.add(entrenador);
        String resultadoEsperado = "Entrenador registrado";
        String resultadoReal = entrenador.validarIdentidad(personas);
        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Validar Identidad");
    }
}