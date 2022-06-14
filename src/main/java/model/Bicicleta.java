package model;

public class Bicicleta extends Vehiculo{

    private String tipo;

    public Bicicleta(String matricula, String descripcion, Color color, Estado estado, TipoVehiculo tipoVehiculo, String tipo) {
        super(matricula,descripcion, color, estado, tipoVehiculo);
        this.tipo=tipo;
    }

    public Bicicleta(String matricula, String tipo) {
        super(matricula);
        this.tipo=tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Bicicleta{" +
                ", tipo=" + tipo +
                '}';
    }
}
