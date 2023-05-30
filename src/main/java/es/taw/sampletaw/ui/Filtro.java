package es.taw.sampletaw.ui;

public class Filtro {
    String FIBANDESTINO;
    String FIBANORIGEN;
    String FDescripcion;
    Double FCantidad;

    public String getFIBANDESTINO() {
        return FIBANDESTINO;
    }

    public void setFIBANDESTINO(String FIBANDESTINO) {
        this.FIBANDESTINO = FIBANDESTINO;
    }

    public String getFIBANORIGEN() {
        return FIBANORIGEN;
    }

    public void setFIBANORIGEN(String FIBANORIGEN) {
        this.FIBANORIGEN = FIBANORIGEN;
    }

    public String getFDescripcion() {
        return FDescripcion;
    }

    public void setFDescripcion(String FDescripcion) {
        this.FDescripcion = FDescripcion;
    }

    public Double getFCantidad() {
        return FCantidad;
    }

    public void setFCantidad(Double FCantidad) {
        this.FCantidad = FCantidad;
    }

    public Filtro() {
    }
}
