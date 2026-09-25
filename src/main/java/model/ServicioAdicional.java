package model;

public class ServicioAdicional {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;
    private TipoServicioAdicional tipoServicio;

    /**
     * Metodo constructor para la clase ServicioAdicional
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param precio
     * @param disponible
     * @param tipoServicio
     */
    public ServicioAdicional(String codigo, String nombre, String descripcion, double precio, boolean disponible, TipoServicioAdicional tipoServicio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.disponible = disponible;
        this.tipoServicio = tipoServicio;
    }

    //Getters y Setters


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public TipoServicioAdicional getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicioAdicional tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    /**
     * Metodo toString para la clase ServicioAdicional
     * @return
     */
    @Override
    public String toString() {
        return "ServicioAdicional{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                ", tipoServicio=" + tipoServicio +
                '}';
    }
}
