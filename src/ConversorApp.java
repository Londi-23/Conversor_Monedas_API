import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConversorApp {

    private static final String API_KEY = "622d0843fc59595fc8fa7b38";

    public double convertir(String base, String destino, double cantidad) {

        double tasa = obtenerTasa(base, destino);
        return cantidad * tasa;
    }

    public double obtenerTasa(String base, String destino) {

        URI direccion = URI.create(
                "https://v6.exchangerate-api.com/v6/"
                        + API_KEY + "/latest/" + base
        );

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser
                    .parseString(response.body())
                    .getAsJsonObject();

            JsonObject rates = json.getAsJsonObject("conversion_rates");

            return rates.get(destino).getAsDouble();

        } catch (Exception e) {
            throw new RuntimeException("Error al consultar la API");
        }
    }
}