import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class ThirdScraping {
    static class InvalidFileTypeException extends Exception {}

    static thirdScraping() {
        //1 - URL do site a ser acessado
        String url = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-tabelas-relacionadas"
        //2 - Conectando e obtendo uma cópia do html inteiro da página
        Document doc = Jsoup.connect(url).get()
        //3 - Obtendo a div através de seu id
        Element div = doc.getElementById("parent-fieldname-text")
        //4 - Obtendo o link através da tag
        Element link = div.getElementsByTag("a").first()
        //6 - Obtendo a url do link através do atributo href
        String href = link.attr("href")
        //7 - Realizando Download do arquivo
        byte[] bytes = Jsoup.connect(href)
                .header("Accept-Encoding", "xlsx, deflate")
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36")
        //.referrer(URL_TO_PARSE)
                .ignoreContentType(true)
                .maxBodySize(0)
                .timeout(400000)
                .execute()
                .bodyAsBytes()

        try {
            FileOutputStream fos = new FileOutputStream("/home/jocelinnunes/Documentos/Proficional/Programação/projetos/Web_Screaping/Download/errors.xlsx")
            fos.write(bytes)
            fos.close()

            System.out.println("File has been downloaded.")
        } catch (IOException e) {
            System.err.println("Could not read the file at '" + href + "'.")
        }
        catch (InvalidFileTypeException e) {
            System.err.println("'" + href + "' does not appear to point to an zip file.")
        }
    }
}
