package org.example.springboot.report_24_제어의역전_IoC;

interface Bean {
    String name();

}

class ColombiaBean implements Bean{

    @Override
    public String name() {
        return "콜롬비아 원두";
    }
}
class EthiopiaBean implements Bean{

    @Override
    public String name() {
        return "에티오피아 원두";
    }
}



