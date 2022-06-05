package model;

public class Moto extends Vehiculo{

    private int velocidadMax;
    private int cilindrada;

    public Moto(String matricula, int preciohora, String marca, String descripcion, Color color, int bateria,
                Estado estado, int idCarnet, String changedBy, TipoVehiculo tipo,int velocidadMax,int cilindrada) {
        super(matricula, preciohora, marca, descripcion, color, bateria, estado, idCarnet, changedBy, tipo);
        this.velocidadMax = velocidadMax;
        this.cilindrada = cilindrada;
    }

    public Moto(String matricula,int velocidadMax,int cilindrada) {
        super(matricula);
        this.velocidadMax = velocidadMax;
        this.cilindrada = cilindrada;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String toString() {
        return "Moto{" +
                ", velocidadMax=" + velocidadMax +
                ", cilindrada='" + cilindrada + '\'' +
                '}';
    }
}
