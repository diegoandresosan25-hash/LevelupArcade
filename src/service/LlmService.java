package service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LlmService {
    private static final String API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private static final String API_KEY = System.getenv("OPENROUTER_API_KEY");
    private static final String MODEL = "openrouter/owl-alpha";

    public String preguntar(String prompt) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            return "❌ API Key no configurada. Configura la variable de entorno OPENROUTER_API_KEY.";
        }
        try {
            JsonObject mensaje = new JsonObject();
            mensaje.addProperty("role", "user");
            mensaje.addProperty("content", prompt);

            JsonArray mensajes = new JsonArray();
            mensajes.add(mensaje);

            JsonObject body = new JsonObject();
            body.addProperty("model", MODEL);
            body.add("messages", mensajes);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .header("HTTP-Referer", "https://leveluparcade.app")
                .header("X-Title", "LevelUpArcade")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
            

            JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();

            if (json.has("error")) {
                return "❌ Error de la API: " + json.getAsJsonObject("error").get("message").getAsString();
            }

            return json.getAsJsonArray("choices")
                       .get(0).getAsJsonObject()
                       .getAsJsonObject("message")
                       .get("content").getAsString();

        } catch (IOException | InterruptedException e) {
            return "❌ Error al conectar con la IA: " + e.getMessage();
        }
    }

    public String generarDescripcion(String nombreProducto) {
        String prompt = "Genera una descripción comercial corta y atractiva para un videojuego o producto de merchandising llamado: " + nombreProducto + ". Máximo 2 frases.";
        return preguntar(prompt);
    }

    public String sugerirCategoria(String nombreProducto) {
        String prompt = "Sugiere una única categoría para este videojuego o producto: " + nombreProducto + ". Las categorías posibles son: Aventura, RPG, Acción, Deportes, Estrategia, Simulación, Merchandising. Responde solo con el nombre de la categoría.";
        return preguntar(prompt);
    }
}