package org.tallerjava.moduloPeaje.infraestructura.messaging;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonWriter;

import java.io.StringReader;
import java.io.StringWriter;

public record PeajeRealizadoMessage(
        String tag,
        String matricula
) {
    public String toJson() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("tag", this.tag)
                .add("matricula", this.matricula)
                .build();

        StringWriter sw = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(sw);
        jsonWriter.write(jsonObject);
        jsonWriter.close();
        return sw.toString();
    }

    public static PeajeRealizadoMessage buildFromJson(String jsonPeajeRealizado) {
        JsonReader jsonReader = Json.createReader(new StringReader(jsonPeajeRealizado));
        JsonObject objeto = jsonReader.readObject();
        PeajeRealizadoMessage peaje = new PeajeRealizadoMessage(
                objeto.getString("tag"),
                objeto.getString("matricula")
        );
        return peaje;
    }
}
