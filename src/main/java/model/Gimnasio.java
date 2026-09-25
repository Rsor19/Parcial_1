package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Administra en memoria los planes registrados en el gimnasio. */
public class Gimnasio {
    private final List<Plan> planes = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();

    public List<Plan> getPlanes() {
        return Collections.unmodifiableList(planes);
    }

    public void agregarPlan(Plan plan) {
        Objects.requireNonNull(plan, "El plan no puede ser null");
        if (buscarPlan(plan.getId()) != null) {
            throw new IllegalArgumentException("Ya existe un plan con ese identificador");
        }
        planes.add(plan);
    }

    public Plan buscarPlan(String id) {
        if (id == null) return null;
        return planes.stream().filter(p -> id.equalsIgnoreCase(p.getId())).findFirst().orElse(null);
    }

    public boolean actualizarPlan(String id, Plan planActualizado) {
        Objects.requireNonNull(planActualizado, "El plan actualizado no puede ser null");
        Plan existente = buscarPlan(id);
        if (existente == null) return false;
        Plan duplicado = buscarPlan(planActualizado.getId());
        if (duplicado != null && duplicado != existente) {
            throw new IllegalArgumentException("Ya existe un plan con ese identificador");
        }
        int indice = planes.indexOf(existente);
        planes.set(indice, planActualizado);
        return true;
    }

    public boolean eliminarPlan(String id) {
        Plan plan = buscarPlan(id);
        return plan != null && planes.remove(plan);
    }

    public void agregarCliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser null");
        if (buscarClientePorTelefono(cliente.getTelefono()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con ese teléfono");
        }
        clientes.add(cliente);
    }

    public Cliente buscarClientePorTelefono(String telefono) {
        if (telefono == null || telefono.isBlank()) return null;
        return clientes.stream()
                .filter(cliente -> telefono.equals(cliente.getTelefono()))
                .findFirst()
                .orElse(null);
    }

    /** Retorna true cuando la suma de los dígitos del teléfono es un número perfecto. */
    public boolean validarTelefonoNumeroPerfecto(Cliente cliente) {
        if (cliente == null || cliente.getTelefono() == null || cliente.getTelefono().isBlank()) return false;
        int sumaDigitos = 0;
        for (char caracter : cliente.getTelefono().toCharArray()) {
            if (!Character.isDigit(caracter)) return false;
            sumaDigitos += Character.digit(caracter, 10);
        }
        return esNumeroPerfecto(sumaDigitos);
    }

    private boolean esNumeroPerfecto(int numero) {
        if (numero < 2) return false;
        int sumaDivisores = 1;
        for (int divisor = 2; divisor <= numero / divisor; divisor++) {
            if (numero % divisor == 0) {
                sumaDivisores += divisor;
                int pareja = numero / divisor;
                if (pareja != divisor) sumaDivisores += pareja;
            }
        }
        return sumaDivisores == numero;
    }
}
