package model;

import java.sql.Date;

public class Bicicleta extends Vehiculo{

    private String tipo;

    public Bicicleta(String matricula, int precioHora, String marca, String descripcion, Color color, int bateria, Date fechaadq, Estado estado, int idCarnet, TipoVehiculo tipoVehiculo, String tipo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaadq, estado, idCarnet, tipoVehiculo);
        this.tipo = tipo;
    }

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
