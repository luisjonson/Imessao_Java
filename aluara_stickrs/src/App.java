import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        String api = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        String url = api;
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
        var geradora  = new GeradoraDeFigurinha();
        for (Map<String,String> filme : listDeFilmes) {
            
        String urlImagem = filme.get("image");
        String titulo = filme.get("title");

        InputStream inputStream = new URL(urlImagem).openStream();
        String nomeArquivo = titulo;

        geradora.cria(inputStream, nomeArquivo);

        System.out.println(titulo);
        System.out.println();
        }
    }
}
