package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.time.LocalDate;

/** Administra en memoria los clientes, planes y operaciones del gimnasio. */
public class Gimnasio {
    private final List<PlanEntrenamiento> planes = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Entrenador> entrenadores = new ArrayList<>();
    private final List<ServicioAdicional> servicios = new ArrayList<>();
    private final List<Inscripcion> inscripciones = new ArrayList<>();
    private final List<Pago> pagos = new ArrayList<>();

    /** Crea un gimnasio con sus colecciones de registros vacías. */
    public Gimnasio() { }

    public List<PlanEntrenamiento> getPlanes() {
        return Collections.unmodifiableList(planes);
    }

    /** Registra un plan si su código todavía no está en uso.
     * @param planEntrenamiento plan que se quiere registrar
     * @throws IllegalArgumentException si ya existe un plan con ese código
     */
    public void agregarPlan(PlanEntrenamiento planEntrenamiento) {
        Objects.requireNonNull(planEntrenamiento, "El plan no puede ser null");
        if (buscarPlan(planEntrenamiento.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un plan con ese identificador");
        }
        planes.add(planEntrenamiento);
    }

    /** Busca un plan por código; devuelve {@code null} si no existe.
     * @param id código del plan
     * @return plan encontrado o {@code null}
     */
    public PlanEntrenamiento buscarPlan(String id) {
        if (id == null) return null;
        return planes.stream().filter(p -> id.equalsIgnoreCase(p.getId())).findFirst().orElse(null);
    }

    /** Reemplaza los datos del plan indicado.
     * @param id código actual del plan
     * @param planEntrenamientoActualizado nuevos datos del plan
     * @return {@code true} si se actualizó; {@code false} si no se encontró
     */
    public boolean actualizarPlan(String id, PlanEntrenamiento planEntrenamientoActualizado) {
        Objects.requireNonNull(planEntrenamientoActualizado, "El plan actualizado no puede ser null");
        PlanEntrenamiento existente = buscarPlan(id);
        if (existente == null) return false;
        PlanEntrenamiento duplicado = buscarPlan(planEntrenamientoActualizado.getId());
        if (duplicado != null && duplicado != existente) {
            throw new IllegalArgumentException("Ya existe un plan con ese identificador");
        }
        int indice = planes.indexOf(existente);
        planes.set(indice, planEntrenamientoActualizado);
        return true;
    }

    /** Elimina el plan que coincide con el código indicado.
     * @param id código del plan que se quiere eliminar
     * @return {@code true} si se eliminó; {@code false} si no existía
     */
    public boolean eliminarPlan(String id) {
        PlanEntrenamiento planEntrenamiento = buscarPlan(id);
        return planEntrenamiento != null && planes.remove(planEntrenamiento);
    }

    public List<Cliente> getClientes() { return Collections.unmodifiableList(clientes); }
    public List<Entrenador> getEntrenadores() { return Collections.unmodifiableList(entrenadores); }
    public List<ServicioAdicional> getServicios() { return Collections.unmodifiableList(servicios); }
    public List<Inscripcion> getInscripciones() { return Collections.unmodifiableList(inscripciones); }
    public List<Pago> getPagos() { return Collections.unmodifiableList(pagos); }

    /** Registra un cliente y evita repetir su documento o teléfono.
     * @param cliente cliente que se quiere registrar
     * @throws IllegalArgumentException si el documento o teléfono ya están registrados
     */
    public void agregarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser null");
        if (indiceCliente(cliente.getId()) >= 0 || buscarClientePorTelefono(cliente.getTelefono()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con ese documento o teléfono");
        }
        clientes.add(cliente);
    }

    /** Actualiza los datos del cliente identificado por su documento.
     * @param documento documento actual del cliente
     * @param nuevo datos actualizados
     * @return {@code true} si se actualizó; {@code false} si no se encontró
     */
    public boolean actualizarCliente(String documento, Cliente nuevo) {
        Objects.requireNonNull(nuevo);
        int i = indiceCliente(documento);
        if (i < 0) return false;
        Cliente porDocumento = clientes.stream().filter(c -> c.getId().equalsIgnoreCase(nuevo.getId())).findFirst().orElse(null);
        Cliente porTelefono = buscarClientePorTelefono(nuevo.getTelefono());
        if ((porDocumento != null && porDocumento != clientes.get(i)) || (porTelefono != null && porTelefono != clientes.get(i)))
            throw new IllegalArgumentException("Documento o teléfono ya registrados");
        clientes.set(i, nuevo);
        return true;
    }

    /** Elimina un cliente a partir de su documento.
     * @param documento documento del cliente
     * @return {@code true} si se eliminó; {@code false} si no existía
     */
    public boolean eliminarCliente(String documento) {
        int i = indiceCliente(documento);
        if (i < 0) return false;
        clientes.remove(i);
        return true;
    }

    private int indiceCliente(String documento) {
        if (documento == null) return -1;
        for (int i=0; i<clientes.size(); i++) if (documento.equalsIgnoreCase(clientes.get(i).getId())) return i;
        return -1;
    }

    /** Busca un cliente por su teléfono registrado.
     * @param telefono número de teléfono que se quiere consultar
     * @return cliente encontrado o {@code null} si no hay coincidencia
     */
    public Cliente buscarClientePorTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) return null;
        return clientes.stream()
                .filter(cliente -> telefono.equals(cliente.getTelefono()))
                .findFirst()
                .orElse(null);
    }

    /** Comprueba si el teléfono completo es un número perfecto.
     * @param cliente cliente cuyo teléfono se va a comprobar
     * @return {@code true} si la suma de sus divisores propios coincide con el teléfono
     */
    public boolean validarTelefonoNumeroPerfecto(Cliente cliente) {
        if (cliente == null || cliente.getTelefono() == null || cliente.getTelefono().isBlank()) return false;
        long numero;
        for (char caracter : cliente.getTelefono().toCharArray()) {
            if (!Character.isDigit(caracter)) return false;
        }
        try { numero = Long.parseLong(cliente.getTelefono()); }
        catch (NumberFormatException e) { return false; }
        return esNumeroPerfecto(numero);
    }

    private boolean esNumeroPerfecto(long numero) {
        if (numero < 2) return false;
        long sumaDivisores = 1;
        for (long divisor = 2; divisor <= numero / divisor; divisor++) {
            if (numero % divisor == 0) {
                sumaDivisores += divisor;
                long pareja = numero / divisor;
                if (pareja != divisor) sumaDivisores += pareja;
            }
        }
        return sumaDivisores == numero;
    }

    /** Registra un entrenador si su identificación está disponible.
     * @param entrenador entrenador que se quiere registrar
     * @throws IllegalArgumentException si la identificación ya está registrada
     */
    public void agregarEntrenador(Entrenador entrenador) {
        Objects.requireNonNull(entrenador);
        if (buscarEntrenador(entrenador.getId()) != null) throw new IllegalArgumentException("Ya existe esa identificación");
        entrenadores.add(entrenador);
    }
    /** Busca un entrenador por identificación.
     * @param id identificación del entrenador
     * @return entrenador encontrado o {@code null} si no existe
     */
    public Entrenador buscarEntrenador(String id) { return entrenadores.stream().filter(e -> id != null && id.equalsIgnoreCase(e.getId())).findFirst().orElse(null); }

    /** Actualiza los datos del entrenador indicado.
     * @param id identificación actual
     * @param nuevo datos actualizados
     * @return {@code true} si se actualizó; {@code false} si no se encontró
     */
    public boolean actualizarEntrenador(String id, Entrenador nuevo) {
        int i = entrenadores.indexOf(buscarEntrenador(id)); if (i < 0) return false;
        Entrenador duplicado = buscarEntrenador(nuevo.getId()); if (duplicado != null && duplicado != entrenadores.get(i)) throw new IllegalArgumentException("Ya existe esa identificación");
        entrenadores.set(i, nuevo); return true;
    }
    /** Elimina un entrenador por identificación.
     * @param id identificación del entrenador
     * @return {@code true} si se eliminó; {@code false} si no existía
     */
    public boolean eliminarEntrenador(String id) { Entrenador e = buscarEntrenador(id); return e != null && entrenadores.remove(e); }

    /** Registra un servicio adicional con un código único.
     * @param servicio servicio que se quiere ofrecer
     * @throws IllegalArgumentException si el código ya está en uso
     */
    public void agregarServicio(ServicioAdicional servicio) {
        Objects.requireNonNull(servicio);
        if (buscarServicio(servicio.getCodigo()) != null) throw new IllegalArgumentException("Ya existe ese código de servicio");
        servicios.add(servicio);
    }
    /** Busca un servicio adicional por código.
     * @param codigo código del servicio
     * @return servicio encontrado o {@code null} si no existe
     */
    public ServicioAdicional buscarServicio(String codigo) { return servicios.stream().filter(s -> codigo != null && codigo.equalsIgnoreCase(s.getCodigo())).findFirst().orElse(null); }

    /** Actualiza los datos del servicio identificado por su código actual.
     * @param codigo código actual
     * @param nuevo datos actualizados
     * @return {@code true} si se actualizó; {@code false} si no se encontró
     */
    public boolean actualizarServicio(String codigo, ServicioAdicional nuevo) {
        int i = servicios.indexOf(buscarServicio(codigo)); if (i < 0) return false;
        ServicioAdicional duplicado = buscarServicio(nuevo.getCodigo()); if (duplicado != null && duplicado != servicios.get(i)) throw new IllegalArgumentException("Ya existe ese código de servicio");
        servicios.set(i, nuevo); return true;
    }
    /** Elimina un servicio adicional por código.
     * @param codigo código del servicio
     * @return {@code true} si se eliminó; {@code false} si no existía
     */
    public boolean eliminarServicio(String codigo) { ServicioAdicional s = buscarServicio(codigo); return s != null && servicios.remove(s); }

    /** Registra una inscripción con código único.
     * @param inscripcion inscripción que se quiere registrar
     * @throws IllegalArgumentException si el código ya está en uso
     */
    public void agregarInscripcion(Inscripcion inscripcion) {
        Objects.requireNonNull(inscripcion);
        if (buscarInscripcion(inscripcion.getCodigoInscripcion()) != null) throw new IllegalArgumentException("Ya existe ese código de inscripción");
        inscripciones.add(inscripcion);
    }
    /** Busca una inscripción por código.
     * @param codigo código de la inscripción
     * @return inscripción encontrada o {@code null} si no existe
     */
    public Inscripcion buscarInscripcion(String codigo) { return inscripciones.stream().filter(i -> codigo != null && codigo.equalsIgnoreCase(i.getCodigoInscripcion())).findFirst().orElse(null); }

    /** Actualiza una inscripción existente.
     * @param codigo código actual de la inscripción
     * @param nueva datos actualizados
     * @return {@code true} si se actualizó; {@code false} si no se encontró
     */
    public boolean actualizarInscripcion(String codigo, Inscripcion nueva) {
        int i = inscripciones.indexOf(buscarInscripcion(codigo)); if (i < 0) return false;
        Inscripcion duplicada = buscarInscripcion(nueva.getCodigoInscripcion()); if (duplicada != null && duplicada != inscripciones.get(i)) throw new IllegalArgumentException("Ya existe ese código de inscripción");
        inscripciones.set(i, nueva); return true;
    }
    /** Elimina una inscripción por código.
     * @param codigo código de la inscripción
     * @return {@code true} si se eliminó; {@code false} si no existía
     */
    public boolean eliminarInscripcion(String codigo) { Inscripcion i = buscarInscripcion(codigo); return i != null && inscripciones.remove(i); }

    /** Registra un pago asociado a una inscripción.
     * @param pago pago que se quiere registrar
     * @throws IllegalArgumentException si el código está repetido o faltan datos válidos
     */
    public void agregarPago(Pago pago) {
        Objects.requireNonNull(pago, "El pago no puede ser null");
        if (buscarPago(pago.getCodigo()) != null) throw new IllegalArgumentException("Ya existe ese código de pago");
        if (pago.getInscripcion() == null || pago.getFechaPago() == null || pago.getMetodo() == null || pago.getMonto() < 0)
            throw new IllegalArgumentException("Completa los datos del pago con valores válidos");
        pagos.add(pago);
    }
    /** Busca un pago por código.
     * @param codigo código del pago
     * @return pago encontrado o {@code null} si no existe
     */
    public Pago buscarPago(String codigo) { return pagos.stream().filter(p -> codigo != null && codigo.equalsIgnoreCase(p.getCodigo())).findFirst().orElse(null); }

    /** Actualiza un pago que ya está registrado.
     * @param codigo código actual del pago
     * @param nuevo datos actualizados
     * @return {@code true} si se actualizó; {@code false} si no se encontró
     */
    public boolean actualizarPago(String codigo, Pago nuevo) {
        Objects.requireNonNull(nuevo, "El pago actualizado no puede ser null");
        int i = pagos.indexOf(buscarPago(codigo)); if (i < 0) return false;
        Pago duplicado = buscarPago(nuevo.getCodigo());
        if (duplicado != null && duplicado != pagos.get(i)) throw new IllegalArgumentException("Ya existe ese código de pago");
        if (nuevo.getInscripcion() == null || nuevo.getFechaPago() == null || nuevo.getMetodo() == null || nuevo.getMonto() < 0)
            throw new IllegalArgumentException("Completa los datos del pago con valores válidos");
        pagos.set(i, nuevo); return true;
    }
    /** Elimina un pago por código.
     * @param codigo código del pago
     * @return {@code true} si se eliminó; {@code false} si no existía
     */
    public boolean eliminarPago(String codigo) { Pago pago = buscarPago(codigo); return pago != null && pagos.remove(pago); }

    /** Suma los pagos recibidos dentro de un periodo, incluyendo sus fechas límite.
     * @param desde primer día del periodo
     * @param hasta último día del periodo
     * @return total pagado durante el periodo
     */
    public double calcularPagos(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null || hasta.isBefore(desde)) throw new IllegalArgumentException("Periodo de fechas no válido");
        return pagos.stream().filter(p -> p.getFechaPago() != null && !p.getFechaPago().isBefore(desde) && !p.getFechaPago().isAfter(hasta))
                .mapToDouble(Pago::getMonto).sum();
    }

    /** Suma el valor de las inscripciones creadas dentro del periodo indicado.
     * @param desde primer día del periodo
     * @param hasta último día del periodo
     * @return total generado por las inscripciones del periodo
     */
    public double calcularIngresos(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null || hasta.isBefore(desde)) throw new IllegalArgumentException("Periodo de fechas no válido");
        return inscripciones.stream().filter(i -> i.getFechaInscripcion() != null
                        && !i.getFechaInscripcion().isBefore(desde) && !i.getFechaInscripcion().isAfter(hasta))
                .mapToDouble(Inscripcion::calcularValorTotal).sum();
    }
}
