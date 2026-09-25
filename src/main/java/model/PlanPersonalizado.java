package model;

public class PlanPersonalizado extends PlanEntrenamiento {
    private int cantidadSesionesEntrenador;
    private EspecialidadEntrenador especialidadRequerida;
    private String objetivosCliente;

    /** Crea un plan personalizado con valores iniciales que luego se completan según el cliente.
     * @param codigo código único del plan
     */
    public PlanPersonalizado(String codigo) {
        super(codigo, 120.0);
    }

    public int getCantidadSesionesEntrenador() { return cantidadSesionesEntrenador; }
    public void setCantidadSesionesEntrenador(int cantidad) { this.cantidadSesionesEntrenador = cantidad; }
    public EspecialidadEntrenador getEspecialidadRequerida() { return especialidadRequerida; }
    public void setEspecialidadRequerida(EspecialidadEntrenador especialidad) { this.especialidadRequerida = especialidad; }
    public String getObjetivosCliente() { return objetivosCliente; }
    public void setObjetivosCliente(String objetivos) { this.objetivosCliente = objetivos; }

    @Override
    public double calcularPrecioFinal() {
        return getValorMensual() * getDuracionMeses();
    }
}
