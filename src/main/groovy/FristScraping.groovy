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
        List<Passing> arquivosObjects = new ArrayList<>()
        //7 - Iterando por cada tr e convertendo-o em um Passing. Em seguida inserindo-o na lista de Passings
        for (Element arquivo: arquivos) {
            //Listando todos os atributos do tr em questão
            List<Element> attributes = arquivo.getElementsByTag("td");
            //criando o objeto e inserindo nele os atributos extraidos do td
            Passing passing = new Passing();
            passing.setName(attributes.get(0).text());
            passing.setPassYds(attributes.get(1).text());
            passing.setYdsAtt(attributes.get(2).text());
            passing.setAtt(attributes.get(3).text());
            //adicionando o objeto na lista de objetos Passing
            arquivosObjects.add(passing);
        
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
