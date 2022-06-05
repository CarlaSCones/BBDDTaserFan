package model;

public class Coche extends Vehiculo{

    private int numPlazas;
    private int numPuertas;

    public Coche(String matricula, int preciohora, String marca, String descripcion, Color color, int bateria, Estado estado, int idCarnet, String changedBy, TipoVehiculo tipo, int numPlazas, int numPuertas){
        super(matricula, preciohora, marca, descripcion, color, bateria, estado, idCarnet, changedBy, tipo);
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
