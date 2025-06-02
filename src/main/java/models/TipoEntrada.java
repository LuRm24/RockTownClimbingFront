package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un tipo de entrada que se puede adquirir en el rocódromo Rocktown
 * Climbing.
 * <p>
 * Define las características de una entrada, como su tipo (ej. bono mensual,
 * entrada suelta), descripción, público al que va dirigida, frecuencia de uso,
 * precio y si incluye comida o bebida. También puede contener notas
 * adicionales.
 *
 * Se utiliza para configurar y ofrecer distintos productos dentro del sistema
 * de venta de entradas.
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class TipoEntrada {

    private Long id;
    private String tipo;
    private String descripcion;
    private String publico_destino;
    private String frecuencia;
    private double precio;
    private boolean bebida_incluida;
    private boolean comida_incluida;
    private String notas;

    private List<Entrada> entradas = new ArrayList<>();

    public TipoEntrada(Long id, String tipo, String descripcion, String publico_destino, String frecuencia, double precio, boolean bebida_incluida, boolean comida_incluida, String notas) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.publico_destino = publico_destino;
        this.frecuencia = frecuencia;
        this.precio = precio;
        this.bebida_incluida = bebida_incluida;
        this.comida_incluida = comida_incluida;
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
        return publico_destino;
    }

    public void setPublicoDestino(String publicoDestino) {
        this.publico_destino = publicoDestino;
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

    public boolean isBebidaIncluida() {
        return bebida_incluida;
    }

    public void setBebidaIncluida(boolean bebidaIncluida) {
        this.bebida_incluida = bebidaIncluida;
    }

    public boolean isComidaIncluida() {
        return comida_incluida;
    }

    public void setComidaIncluida(boolean comidaIncluida) {
        this.comida_incluida = comidaIncluida;
    }

    /**
     * Devuelve una representación textual del tipo de entrada, útil para
     * mostrar en listados.
     *
     * @return Cadena en formato "descripción - público - tipo"
     */
    @Override
    public String toString() {
        return descripcion + "-" + publico_destino + "-" + tipo;
    }

    /**
     * Compara este tipo de entrada con otro en base a su identificador.
     *
     * @param o Objeto a comparar
     * @return {@code true} si los IDs son iguales, {@code false} en caso
     * contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoEntrada)) {
            return false;
        }
        TipoEntrada that = (TipoEntrada) o;
        return this.id.equals(that.id);
    }
}
