package model;

public enum Estado {
    BAJA("baja"), TALLER("taller"), PREPARADO("preparado"), RESERVADO("reservado"), ALQUILADO("alquilado");

    private String stringEstado;
    Estado(String stringEstado) {
        this.stringEstado = stringEstado;
    }

    public String getStringEstado(){
        return stringEstado;
    }
}
