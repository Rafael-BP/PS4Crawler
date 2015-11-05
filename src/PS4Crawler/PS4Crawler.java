package PS4Crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PS4Crawler
{
  // Fake USER_AGENT
    private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private Document htmlDocument;


    /**
     * Visito a página e printo o nome de cada artigo encontrado até o numero requisitado
     * @param url
     * @return sucesso ou erro
     */
    public boolean buscarArtigosPS4(String url, int quantidade)
    {
        try {
            int qtde = quantidade;
            String urlAtual = url;
            while(qtde > 0) {
                Connection connection = Jsoup.connect(urlAtual).userAgent(USER_AGENT);
                Document htmlDocument = connection.get();

                this.htmlDocument = htmlDocument;
                if(connection.response().statusCode() == 200) {
                    System.out.println("\n**Visitando** Pagina: " + urlAtual);
                }
                if(!connection.response().contentType().contains("text/html")) {
                    System.out.println("**Falha** Deu ruim");
                    return false;
                }

                Elements artigos = htmlDocument.select("h1[class=articleTitle] > a[class=articleLink]");
                System.out.println("Achou (" + artigos.size() + ") artigos");
                for(Element artigo : artigos) {
                    if (qtde > 0) {
                        System.out.println(artigo.text());
                        qtde--;
                    }
                }   
                
                if(qtde > 0) {
                    urlAtual = htmlDocument.select("div[class=articleRightLink] > a[href]").attr("abs:href");
                }
            }
            return true;
        } catch(IOException error) {
            return false;
        }
    }
  
}

