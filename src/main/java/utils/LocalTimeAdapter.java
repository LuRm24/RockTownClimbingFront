/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Adaptador personalizado para la clase {@link LocalTime}, utilizado por la
 * librería Gson.
 * <p>
 * Permite convertir objetos {@code LocalTime} a formato de texto ISO-8601
 * (ejemplo: "14:30:00") y viceversa, para que puedan ser serializados y
 * deserializados correctamente en JSON.
 * <p>
 * Este adaptador es necesario porque Gson no incluye soporte nativo para clases
 * de tiempo de {@code java.time}.
 *
 * <p>
 * Ejemplo de uso con Gson:
 * <pre>{@code
 * Gson gson = new GsonBuilder()
 *     .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
 *     .create();
 * }</pre>
 *
 * @author Lucia Rodriguez Martin
 * @version 1.0
 */
public class LocalTimeAdapter extends TypeAdapter<LocalTime> {

    /**
     * Formateador de tiempo en formato ISO (HH:mm:ss).
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    /**
     * Serializa un objeto {@code LocalTime} como una cadena de texto en formato
     * ISO.
     *
     * @param out Objeto {@code JsonWriter} al que se escribe
     * @param value Hora a serializar
     * @throws IOException Si ocurre un error al escribir el JSON
     */
    @Override
    public void write(JsonWriter out, LocalTime value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value.toString()); // Por defecto en formato HH:mm:ss
        }
    }

    /**
     * Deserializa una cadena de texto en formato hora a un objeto
     * {@code LocalTime}.
     *
     * @param in Objeto {@code JsonReader} del que se lee la hora
     * @return Instancia de {@code LocalTime} correspondiente al texto leído
     * @throws IOException Si ocurre un error al leer el JSON
     */
    @Override
    public LocalTime read(JsonReader in) throws IOException {
        return LocalTime.parse(in.nextString(), FORMATTER);
    }
}
