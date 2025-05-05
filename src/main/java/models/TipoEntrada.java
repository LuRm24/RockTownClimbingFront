package models;
import java.util.ArrayList;
import java.util.List;

public class TipoEntrada {
    private Long id;
    private String tipo;
    private String descripcion;
    private String publicoDestino;
    private String frecuencia;
    private double precio;
    private String notas;

    private List<Entrada> entradas = new ArrayList<>();

    public TipoEntrada(Long id, String tipo, String descripcion, String publicoDestino, String frecuencia, double precio, String notas) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.publicoDestino = publicoDestino;
        this.frecuencia = frecuencia;
        this.precio = precio;
        this.notas = notas;
    }

    @Override
    public String toString() {
        return descripcion + "-" + publicoDestino + "-" + tipo;
    }
}