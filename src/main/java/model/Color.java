package model;

public enum Color {

    ROJO("rojo"), AMARILLO("amarillo"), VERDE("verde"), AZUL("azul"), BLANCO("blanco"), NEGRO("negro");

    private String stringColor;
    Color(String stringColor){
        this.stringColor = stringColor;
    }
    public String getStringColor(){
        return stringColor;
    }
}
