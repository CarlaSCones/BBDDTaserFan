package model;

public class Bicicleta extends Vehiculo{

   // private String matricula;
    private String tipo;

    public Bicicleta(String matricula, int preciohora, String marca, String descripcion, Color color, int bateria, Estado estado, int idCarnet, String changedBy, TipoVehiculo tipoVehiculo, String tipo) {
        super(matricula, preciohora, marca, descripcion, color, bateria, estado, idCarnet, changedBy, tipoVehiculo);
        this.tipo=tipo;
    }

    public Bicicleta(String matricula, String tipo) {
        super(matricula);
        this.tipo=tipo;
    }
   /* public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }*/

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
