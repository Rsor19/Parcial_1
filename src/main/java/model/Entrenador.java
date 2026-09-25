package model;

import java.util.List;

public class Entrenador extends Persona {
    private EspecialidadEntrenador especialidad;
    private double tarifaSesion;

    /** Crea un entrenador con su especialidad y tarifa por sesión.
     * @param id identificación del entrenador
     * @param nombre nombre completo
     * @param telefono teléfono de contacto
     * @param correo correo electrónico
     * @param especialidad área en la que trabaja
     * @param tarifaSesion valor que cobra por sesión
     */
    public Entrenador(String id, String nombre, String telefono, String correo, EspecialidadEntrenador especialidad, double tarifaSesion) {
        super(id, nombre, telefono, correo);
        this.especialidad = especialidad;
        this.tarifaSesion = tarifaSesion;
    }

    //Getters y Setters


    public EspecialidadEntrenador getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEntrenador especialidad) {
        this.especialidad = especialidad;
    }

    public double getTarifaSesion() {
        return tarifaSesion;
    }

    public void setTarifaSesion(double tarifaSesion) {
        this.tarifaSesion = tarifaSesion;
    }

    /**
     * Metodo toString de la clase Entrenador
     *
     * @return
     */
    @Override
    public String toString() {
        return getNombre() + " (" + getEspecialidad() + ")";
    }

    /**
     * Sobreescritura del metodo validarIdentidad para la clase Entrenador
     *
     * @return
     */
    @Override
    public String validarIdentidad(List<Persona> listaPersonas) {
        for (Persona e : listaPersonas) {
            if (e.getId().equals(this.getId())) {
                return "Entrenador registrado";
            }
        }
        return "Entrenador no esta registrado";
    }
}
