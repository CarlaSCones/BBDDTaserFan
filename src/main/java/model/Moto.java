package model;

public class Moto extends Vehiculo{

    private int velocidadMax;
    private int cilindrada;

    public Moto(String matricula, String descripcion, Color color, Estado estado, TipoVehiculo tipo,int velocidadMax,int cilindrada) {
        super(matricula, descripcion, color, estado, tipo);
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
