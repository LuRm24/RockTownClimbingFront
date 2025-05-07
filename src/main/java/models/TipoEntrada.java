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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPublicoDestino() {
        return publicoDestino;
    }

    public void setPublicoDestino(String publicoDestino) {
        this.publicoDestino = publicoDestino;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }
    

    @Override
    public String toString() {
        return descripcion + "-" + publicoDestino + "-" + tipo;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoEntrada)) return false;
        TipoEntrada that = (TipoEntrada) o;
        return this.id.equals(that.id);
    }
}