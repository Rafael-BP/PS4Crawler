package PS4Crawler;

public class RobozinTest
{
    /**
     * Teste buscando novas noticias
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        PS4Crawler crawler = new PS4Crawler();
        crawler.buscarArtigosPS4("http://www.playstationlifestyle.net/category/ps4/", 30);
    }
}