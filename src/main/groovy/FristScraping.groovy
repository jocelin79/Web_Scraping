import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class FristScraping {
  
  static class InvalidFileTypeException extends Exception {}
  
  static fristScraping() {
      //1 - URL do site a ser acessado
      String url = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-2013-setembro-2022"
      //2 - Conectando e obtendo uma cópia do html inteiro da página
      Document doc = Jsoup.connect(url).get()
      //3 - Obtendo a tabela através de sua classe
      Element table = doc.getElementsByClass("table table-bordered").first()
      //4 - Obtendo o corpo da tabela através da tag
      Element tbody = table.getElementsByTag("tbody").first()
      //5 - Criando uma lista de todos os tr's do tbody obtido
      List<Element> arquivos = tbody.getElementsByTag("tr")
      //6 - Criando uma lista vazia para Guardar os dados dos tr's convertidos
      List<Element> atributesList = new ArrayList<>()
      //7 - Iterando por cada tr e extraindo o href alvo
      Element link
      String href
      for (Element arquivo: arquivos) {
          //Listando todos os atributos do tr em questão
          List<Element> attributes = arquivo.getElementsByTag("td");

          if (attributes.get(0).text() == "Componente de Comunicação") {

              link = attributes.get(2).getElementsByTag("a").first()
              href = link.attr("href")
          }
      }

      byte[] bytes = Jsoup.connect(href)
              .header("Accept-Encoding", "zip, deflate")
              .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36")
              //.referrer(URL_TO_PARSE)
              .ignoreContentType(true)
              .maxBodySize(0)
              .timeout(400000)
              .execute()
              .bodyAsBytes()


      try {
          //String savedFileName = link.text()
          //if (!savedFileName.endsWith(".zip")) savedFileName.concat(".zip")
          println("OK01")
          FileOutputStream fos = new FileOutputStream("/home/jocelinnunes/Documentos/Proficional/Programação/projetos/Web_Screaping/Download/communicate.zip")
          println ("OK02")
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
