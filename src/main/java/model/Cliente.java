package model;

/** Cliente identificado por su número de teléfono. */
public class Cliente {
    private String telefono;

    public Cliente(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
