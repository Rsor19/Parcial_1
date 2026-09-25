package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class GimnasioTest {
    private static final Logger LOG = Logger.getLogger(GimnasioTest.class.getName());

    /**
     * Test para que el metodo agregarCliente agregue a los clientes correctamente
     */
    @Test
    void agregarCliente() {
        LOG.info("Inicio Test Agregar Cliente");
        Gimnasio gimnasio = new Gimnasio();
        Cliente cliente = new Cliente("1001", "Juan Perez", "3001112233", "juan@correo.com", 25, LocalDate.now());
        gimnasio.agregarCliente(cliente);
        List<Cliente> resultadoEsperado = new ArrayList<>();
        resultadoEsperado.add(cliente);
        Assertions.assertIterableEquals(resultadoEsperado, gimnasio.getClientes());

        LOG.info("Fin Test Agregar Cliente");
    }
    /**
     * Test para que el metodo actualizarCliente actualice a los clientes correctamente
     */
    @Test
    void actualizarCliente() {
        LOG.info("Inicio Test Actualizar Cliente");

        Gimnasio gimnasio = new Gimnasio();
        Cliente original = new Cliente("1001", "Juan Perez", "3001112233", "juan@correo.com", 25, LocalDate.now());
        gimnasio.agregarCliente(original);
        Cliente actualizado = new Cliente("1001", "Juan Perez Actualizado", "3005556677", "nuevo@correo.com", 26, LocalDate.now());
        boolean resultado = gimnasio.actualizarCliente("1001", actualizado);
        Assertions.assertTrue(resultado);

        LOG.info("Fin Test Actualizar Cliente");
    }

    /**
     * Test para que el metodo eliminarCliente elimine a los clientes correctamente
     */
    @Test
    void eliminarCliente() {
        LOG.info("Inicio Test Eliminar Cliente");

        Gimnasio gimnasio = new Gimnasio();
        Cliente cliente = new Cliente("1001", "Juan Perez", "3001112233", "juan@correo.com", 25, LocalDate.now());
        gimnasio.agregarCliente(cliente);
        boolean resultado = gimnasio.eliminarCliente("1001");
        Assertions.assertTrue(resultado);
        LOG.info("Fin Test Eliminar Cliente");
    }

    /**
     * Test para que el metodo buscarClientePorTelefono encuentre a un cliente por medio de su numero de telefono
     */
    @Test
    void buscarClientePorTelefono() {
        LOG.info("Inicio Test Buscar Cliente Por Telefono");

        Gimnasio gimnasio = new Gimnasio();
        Cliente cliente = new Cliente("1001", "Juan Perez", "3001112233", "juan@correo.com", 25, LocalDate.now());
        gimnasio.agregarCliente(cliente);
        Cliente resultado = gimnasio.buscarClientePorTelefono("3001112233");
        Assertions.assertEquals(cliente, resultado);

        LOG.info("Fin Test Buscar Cliente Por Telefono");
    }

    /**
     * Test para que el metodo calcularPagos logre calcular los pagos correctamente sin errores
     */
    @Test
    void calcularPagos() {
        LOG.info("Inicio Test Calcular Pagos");

        Gimnasio gimnasio = new Gimnasio();
        Inscripcion inscripcion = new Inscripcion("INS-1", LocalDate.of(2026, 1, 1), 0);
        Pago pago = new Pago("PAG-1", inscripcion, LocalDate.of(2026, 3, 10), 50000, MetodoPago.EFECTIVO);
        gimnasio.agregarPago(pago);
        double resultadoEsperado = 50000;
        double resultadoReal = gimnasio.calcularPagos(LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 31));
        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Calcular Pagos");
    }

    /**
     * Test para que el metodo calcularIngresos calcule los ingresos totales del gimnasio correctamente
     */
    @Test
    void calcularIngresos() {
        LOG.info("Inicio Test Calcular Ingresos");

        Gimnasio gimnasio = new Gimnasio();
        PlanBasico plan = new PlanBasico("PLAN-1");
        Inscripcion inscripcion = new Inscripcion("INS-1", LocalDate.of(2026, 3, 15), 0, null, plan, null, null);
        gimnasio.agregarInscripcion(inscripcion);
        double resultadoEsperado = 50.0;
        double resultadoReal = gimnasio.calcularIngresos(LocalDate.of(2026, 3, 1), LocalDate.of(2026, 3, 31));
        Assertions.assertEquals(resultadoEsperado, resultadoReal);

        LOG.info("Fin Test Calcular Ingresos");
    }

    /**
     * Test para que el metodo validarTelefonoNumeroPerfecto verifique que el numero de telefono de un cliente sea un numero perfecto
     */
    @Test
    void validarTelefonoNumeroPerfecto() {
        LOG.info("Inicio Test Validar Telefono Numero Perfecto");

        Gimnasio gimnasio = new Gimnasio();
        Cliente cliente = new Cliente("1001", "Juan Perez", "28", "juan@correo.com", 25, LocalDate.now());
        boolean resultado = gimnasio.validarTelefonoNumeroPerfecto(cliente);
        Assertions.assertTrue(resultado);

        LOG.info("Fin Test Validar Telefono Numero Perfecto");
    }
}