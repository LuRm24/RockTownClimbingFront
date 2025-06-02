/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Adaptador personalizado para la clase {@link LocalDate}, utilizado por la librería Gson.
 * <p>
 * Esta clase permite serializar objetos {@code LocalDate} como cadenas de texto en formato
 * ISO-8601 (ejemplo: "2025-06-01") y deserializarlos nuevamente en objetos {@code LocalDate}.
 * 
 * Es especialmente útil cuando se utiliza Gson para convertir fechas entre JSON y objetos Java,
 * ya que Gson no soporta {@code java.time.LocalDate} de forma nativa.
 * 
 * <p>Ejemplo de uso con Gson:
 * <pre>{@code
 * Gson gson = new GsonBuilder()
 *     .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
 *     .create();
 * }</pre>
 * 
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class LocalDateAdapter extends TypeAdapter<LocalDate> {
    /**
     * Serializa un objeto {@code LocalDate} como una cadena de texto.
     *
     * @param out Objeto {@code JsonWriter} al que se escribe
     * @param value Fecha a serializar
     * @throws IOException Si ocurre un error al escribir el JSON
     */
    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        if (value == null) {
            out.nullValue();
        }
        else {
           out.value(value.toString()); // Formato ISO-8601: yyyy-MM-dd
        }
    }
     /**
     * Deserializa una cadena de texto en formato fecha a un objeto {@code LocalDate}.
     *
     * @param in Objeto {@code JsonReader} del que se lee la fecha
     * @return Instancia de {@code LocalDate} correspondiente al texto leído
     * @throws IOException Si ocurre un error al leer el JSON
     */
    @Override
    public LocalDate read(JsonReader in) throws IOException {
        return LocalDate.parse(in.nextString());
    }
}