import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

class SecundScraping {

    static secundScraping() {
        //1 - URL do site a ser acessado
        String url = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss"
        //2 - Conectando e obtendo uma cópia do html inteiro da página
        Document doc = Jsoup.connect(url).get()
        //3 - Obtendo o elemento que possui a tabela, através de seu id
        Element divTable = doc.getElementById("parent-fieldname-text")
        //4 - Obtendo a tabela através de sua classe
        Element table = divTable.getElementsByTag("table").first()
        //5 - Obtendo o corpo da tabela através da tag
        Element tbody = table.getElementsByTag("tbody").first()
        //6 - Criando uma lista de todos os tr's do tbody obtido
        List<Element> competencias = tbody.getElementsByTag("tr")
        //7 - Criando uma lista vazia para Guardar os dados dos tr's convertidos
        List<Passing> competenciasObjects = new ArrayList<>()
        //8 - Iterando por cada tr e convertendo-o em um Passing. Em seguida inserindo-o na lista de Passings
        for (Element competencia: competencias) {
            //Listando todos os atributos do tr em questão
            List<Element> attributes = competencia.getElementsByTag("td")
            //criando o objeto e inserindo nele os atributos extraidos do td
            Passing passing = new Passing(
                    attributes.get(0).text(),
                    attributes.get(1).text(),
                    attributes.get(2).text()
            )
            //adicionando o objeto na lista de objetos Passing
            if(passing.competencia == "jan/2016") {
                break
            } else {
                competenciasObjects.add(passing)
            }
        }
        //Por fim, convertendo os objetos do tipo Passing para Json, facilitando a leitura dos dados obtidos do site
        for (Passing passing: competenciasObjects) {
            converterToJson(passing)
        }
    }

    //método para converter um objeto em um Json
    private static void converterToJson(Passing passing){
        ObjectMapper mapper = new ObjectMapper()
        try {
            String json = mapper.writeValueAsString(passing)
            System.out.println("Objeto em JSON: " + json)
        } catch (JsonProcessingException e) {
            e.printStackTrace()
        }
    }
}
