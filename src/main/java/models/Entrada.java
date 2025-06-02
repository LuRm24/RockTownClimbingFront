package models;
/**
 * Representa una entrada adquirida por un cliente para acceder al rocódromo Rocktown Climbing.
 * <p>
 * Cada entrada está asociada a una fecha específica, a un tipo de entrada determinado
 * (por ejemplo, entrada individual, bono mensual, etc.) y al cliente que la ha comprado o utilizado.
 * 
 * Esta clase puede utilizarse para el historial de compras, control de accesos
 * y generación de informes de uso por cliente.
 * 
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
import java.time.LocalDate;

public class Entrada {
    private Long id;
    private LocalDate fecha;

    private TipoEntrada tipo_entrada;

    private Cliente cliente;

}
