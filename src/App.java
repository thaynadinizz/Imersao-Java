import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

//import org.w3c.dom.Text;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        var response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + (".png");

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("------------------------------------------------");
            System.out.println("\u001b[37m \u001b[45m Título \u001b[m" + filme.get("title"));
            System.out.println("\u001b[1m URL da Imagem: \u001b[m " + filme.get("image"));
            System.out.println(filme.get("imDbRating"));

            double classificacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classificacao;

            for (int n = 1; n <= numeroEstrelinhas; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}
// System.out.println("⭐⭐⭐⭐⭐");