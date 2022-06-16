package model;

import java.sql.Date;

public class Patinete extends Vehiculo{

    private int numRuedas;
    private int tamanyo;

    public Patinete(String matricula, int precioHora, String marca, String descripcion, Color color, int bateria, Date fechaadq, Estado estado, int idCarnet, TipoVehiculo tipoVehiculo, int numRuedas, int tamanyo) {
        super(matricula, precioHora, marca, descripcion, color, bateria, fechaadq, estado, idCarnet, tipoVehiculo);
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public Patinete(String matricula, String descripcion, Color color, Estado estado, TipoVehiculo tipo, int numRuedas, int tamanyo) {
        super(matricula, descripcion, color, estado, tipo);
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public Patinete(String matricula, int numRuedas, int tamanyo) {
        super(matricula);
        this.numRuedas = numRuedas;
        this.tamanyo = tamanyo;
    }

    public int getNumRuedas() {
        return numRuedas;
    }

    public void setNumRuedas(int numRuedas) {
        this.numRuedas = numRuedas;
    }

    public int getTamanyo() {
        return tamanyo;
    }

    public void setTamanyo(int tamanyo) {
        this.tamanyo = tamanyo;
    }

    @Override
    public String toString() {
        return "Patinete{" +
                ", numRuedas=" + numRuedas + '\'' +
                ", tamaño='" + tamanyo +
                '}';
    }
}
