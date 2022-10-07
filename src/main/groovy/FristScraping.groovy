class FristScraping {
  
  static class InvalidFileTypeException extends Exception {}
  
  static fristScraping() {
        //1 - URL do site a ser acessado
        String url = "https://jovemnerd.com.br/";
        //2 - Conectando e obtendo uma cópia do html inteiro da página
        Document doc = Jsoup.connect(url).get();
        //3 - Obtendo os artigos por classe
        List<Element> artigos = doc.getElementsByClass("info");

        List<Element> as = new ArrayList<>();
        List<String> hrefs = new ArrayList<>();

        //4 - Obtendo as tags "a" dos artigos
        artigos.forEach(element -> {
            as.add(element.getElementsByTag("a").first());
        });

        //5 - Obtendo as urls das tags a
        as.forEach(element -> {
            hrefs.add(element.attr("href"));
        });
        //6 - Imprimindo os links
       hrefs.forEach(s -> {
           System.out.println("URL: "  + s);
       });
  }    
}
