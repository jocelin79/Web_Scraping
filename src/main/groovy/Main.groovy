static main(String[] args) {
    boolean on = true

    while (on) {
        print("\n\nBaixar os aquivos da documentação do padrão TISS, na versão mais recente: digite 1;\n\n" +
                "Coletar dados de competência, publicação e início de vigência, a partir de jan/2016: digite 2;\n\n" +
                "baixar a Tabela de erros no envio para a ANS: digite 3;\n\n" +
                "Caso deseje Finalizar o atendimento, digite 0.\n\n" +
                "Digite aqui:")

        Scanner s = new Scanner(System.in)
        String response = s.nextLine()

        switch (response) {
            case "1":
                FristScraping fscp = new FristScraping()
                fscp.fristScraping()
                break
            case "2":
                SecundScraping sscp = new SecundScraping()
                sscp.secundScraping()
                break
        }
    }
}