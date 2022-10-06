import org.jsoup.Jsoup

static void main(String[] args) {
    //1 - URL do site a ser acessado
    String url = "https://www.nfl.com/stats/player-stats/";
    //2 - Conectando e obtendo uma cópia do html inteiro da página
    Document doc = Jsoup.connect(url).get();
    //3 - Obtendo a tabela através de sua classe
    Element table = doc.getElementsByClass("d3-o-table d3-o-table--detailed d3-o-player-stats--detailed d3-o-table--sortable").first();
    //4 - Obtendo o corpo da tabela através da tag
    Element tbody = table.getElementsByTag("tbody").first();
    //5 - Criando uma lista de todos os tr's do tbody obtido
    List<Element> players = tbody.getElementsByTag("tr");
    //6 - Criando uma lista vazia para Guardar os dados dos tr's convertidos
    List<Passing> playersObjects = new ArrayList<>();
    //7 - Iterando por cada tr e convertendo-o em um Passing. Em seguida inserindo-o na lista de Passings
    for (Element player: players) {
      //Listando todos os atributos do tr em questão
      List<Element> attributes = player.getElementsByTag("td");
      //criando o objeto e inserindo nele os atributos extraidos do td
      Passing passing = new Passing(
              attributes.get(0).text(),
              attributes.get(1).text(),
              attributes.get(2).text(),
              attributes.get(3).text()
      );
      //adicionando o objeto na lista de objetos Passing
      playersObjects.add(passing);
    }
    //Por fim, convertendo os objetos do tipo Passing para Json, facilitando a leitura dos dados obtidos do site
    /*for (Passing passing: playersObjects) {
        converterToJson(passing);
    } */
  }

  //Bonus: método para converter um objeto em um Json
  private static void converterToJson(Passing passing){
    ObjectMapper mapper = new ObjectMapper();
    try {
      String json = mapper.writeValueAsString(passing);
      System.out.println("Objeto em JSON: " + json);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }
}