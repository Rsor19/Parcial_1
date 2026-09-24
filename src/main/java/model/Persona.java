package model;

public abstract class Persona {
    private String id;
    private String nombre;
    private String telefono;
    private String correo;

    /**
     * Metodo Constructor de la Clase Persona
     * @param id
     * @param nombre
     * @param telefono
     * @param correo
     */

    public Persona(String id, String nombre, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    //Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Metodo toString de la clasePersona
     * @return
     */
    @Override
    public String toString() {
        return "Persona{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }

    /**
     * Metodo para validar la identidad de una persona
     * @return
     */
    public abstract String validarIdentidad();
}
