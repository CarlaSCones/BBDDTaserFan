package model;

public class Vehiculo {

    private String matricula;
    private int preciohora;
    private String marca;
    private String descripcion;
    private Color color;
    private int bateria;
    private Estado estado;
    private int idCarnet;
    private String changedBy;
    private TipoVehiculo tipoVehiculo;

    public Vehiculo(String matricula, int preciohora, String marca, String descripcion, Color color, int bateria, Estado estado, int idCarnet, String changedBy, TipoVehiculo tipoVehiculo) {
        this.matricula = matricula;
        this.preciohora = preciohora;
        this.marca = marca;
        this.descripcion=descripcion;
        this.color = color;
        this.bateria = bateria;
        this.estado = estado;
        this.idCarnet = idCarnet;
        this.changedBy = changedBy;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Vehiculo(String matricula, int preciohora, String marca) {
        this.matricula = matricula;
        this.preciohora = preciohora;
        this.marca = marca;
    }

    public Vehiculo(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPreciohora() {
        return preciohora;
    }

    public void setPreciohora(int preciohora) {
        this.preciohora = preciohora;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getIdCarnet() {
        return idCarnet;
    }

    public void setIdCarnet(int idCarnet) {
        this.idCarnet = idCarnet;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", preciohora=" + preciohora +
                ", marca='" + marca + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", color='" + color + '\'' +
                ", bateria=" + bateria +
                ", estado='" + estado + '\'' +
                ", idCarnet=" + idCarnet +
                ", changedBy='" + changedBy + '\'' +
                '}';
    }
}
