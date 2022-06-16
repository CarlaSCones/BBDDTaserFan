package model;

import java.sql.Date;

public class Coche extends Vehiculo{

    private int numPlazas;
    private int numPuertas;

    public Coche(String matricula, int precioHora, String marca, String descripcion, Color color, int bateria, Date fechaadq, Estado estado, int idCarnet, TipoVehiculo tipoVehiculo, int numPlazas, int numPuertas) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaadq, estado, idCarnet, tipoVehiculo);
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    public Coche(String matricula, String descripcion, Color color, Estado estado, TipoVehiculo tipo, int numPlazas, int numPuertas){
        super(matricula, descripcion, color, estado, tipo);
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    public Coche(String matricula, int numPlazas, int numPuertas){
        super(matricula);
        this.numPlazas = numPlazas;
        this.numPuertas = numPuertas;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public int getNumPuertas() {
        return numPuertas;
    }

    public void setNumPuertas(int numPuertas) {
        this.numPuertas = numPuertas;
    }

    @Override
    public String toString() {
        return "Coche{" +
                ", numPlazas=" + numPlazas +
                ", numPuertas='" + numPuertas + '\'' +
                '}';
    }
}
