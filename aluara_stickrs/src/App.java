import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
               // fazer uma conection http e buscar os top 250

        //String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI uri = URI.create(url);
        HttpClient client = HttpClient.newHttpClient(); 
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);
        
        // pegar só os dados que interessa(titulo , pposte ,clasificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listDeFilmes = parser.parse(body); 
        
        //exibir e manipula os dados
        for (Map<String,String> filme : listDeFilmes) {
           System.out.println("\u001b[37m \u001b[44m Title \u001b[m - " + filme.get("title"));
           System.out.println();
           System.out.println("\u001b[37m \u001b[44m Image \u001b[m - " + filme.get("image"));
           System.out.println("\u001b[37m \u001b[44m Imdb Ranting \u001b[m - " + filme.get("imDbRating"));
           System.out.println("------------------------------------------------------------");
        }
    }
}
