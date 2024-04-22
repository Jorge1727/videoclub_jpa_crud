package org.iesvdm.videoclub.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;

import java.io.IOException;

public class CategoriaSerializer extends JsonSerializer<Categoria> {

    @Override
    public void serialize(Categoria categoria, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", categoria.getId());
        jsonGenerator.writeStringField("nombre", categoria.getNombre());
        jsonGenerator.writeFieldName("peliculas");
        jsonGenerator.writeStartArray();
        for (Pelicula pelicula : categoria.getPeliculas()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("idPelicula", pelicula.getIdPelicula());
            jsonGenerator.writeStringField("titulo", pelicula.getTitulo());
            // Serialize other properties of Pelicula if needed
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        if (categoria.getUltimaActualizacion() != null) {
            jsonGenerator.writeStringField("ultimaActualizacion", categoria.getUltimaActualizacion().toString());
        } else {
            // Opciones para manejar el valor nulo:

            // 1. Escribir un marcador de posición:
            jsonGenerator.writeStringField("ultimaActualizacion", "");

            // 2. Omitir el campo:
            // No se incluye "ultima_actualizacion" en la salida JSON.
        }
        jsonGenerator.writeEndObject();
    }
}
