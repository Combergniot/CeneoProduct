package com.webmodels.data;

import com.webmodels.model.CeneoProduct;
import com.webmodels.service.CeneoProductService;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//TODO - Generalnie warto zastanowic sie czy nie korzystac z API Ceneo https://pp.ceneo.pl/api/
@Component
public class CeneoProductCollector extends ScrapperSettings {

    @Autowired
    CeneoProductService ceneoProductService;

    // TODO   pobieranie symbolu modelu z bazy - do zastapienia
    private List<String> getAllModels() {
        List<String> modelSymbolList = new ArrayList<>();
        modelSymbolList.add("tx-50dx730e");
        modelSymbolList.add("dmc-gm5w");
        modelSymbolList.add("DMC-G7EG-S");
        modelSymbolList.add("TX-55CX740E");
        modelSymbolList.add("dmc-gx80");
        return modelSymbolList;
    }

    private String createLinkFromSymbol(String modelSymbol) {
        return "https://www.ceneo.pl/;szukaj-" + modelSymbol + "+panasonic";
    }

    //    TODO - mozna ewentualnie CeneoPruduct przerobic i dodac do niego symbol modelu
    public List<CeneoProduct> createProductListFromSymbol(String modelSymbol) throws Exception {
        String link = createLinkFromSymbol(modelSymbol);
        Elements elements = collectElements(link);
        List<CeneoProduct> products = new ArrayList<>();
        for (Element element : elements) {
            CeneoProduct ceneoProduct = downloadProductData(element);
            products.add(ceneoProduct);
            ceneoProductService.save(ceneoProduct);
        }
        return products;
    }

    private List<String> createLinkList() {
        List<String> modelSymbolList = getAllModels();
        List<String> linkList = new ArrayList<>();
        for (int i = 0; i < modelSymbolList.size(); i++) {
            linkList.add("https://www.ceneo.pl/;szukaj-" + modelSymbolList.get(i) + "+panasonic");
        }
        return linkList;
    }

//    Todo - do wrzucenia odpowiednia metoda z panamodels w miejsce Document document = ...
    protected Elements collectElements(String link) throws Exception {
        Document document = connectWith(link);
        Elements elements = document.select("div.cat-prod-row-content");
        return elements;
    }

    public void collectData() throws Exception {
        List<String> linkList = createLinkList();
        for (int i = 0; i < linkList.size(); i++) {
            Elements elements = collectElements(linkList.get(i));
            for (Element element : elements) {
                CeneoProduct ceneoProduct = downloadProductData(element);
                ceneoProductService.save(ceneoProduct);
            }
        }
    }

    protected CeneoProduct downloadProductData(Element element) {
        CeneoProduct ceneoProduct = new CeneoProduct();
        ceneoProduct.setTitle(searchForTitle(element));
        ceneoProduct.setCategory(searchForCategory(element));
        ceneoProduct.setLink(searchForLink(element));
        ceneoProduct.setPrice(searchForPrice(element));
        return ceneoProduct;
    }

    protected double searchForPrice(Element element) {
        String price = element.select("span.price-format.nowrap").text();
        String onlyPrice = price
                .replaceAll("zł", "")
                .replaceAll(",", ".");
        return Double.valueOf(onlyPrice);
    }

    //    Wywal abs: w atrybucie jeśli chcesz tylko identyfikator do linku
    //    Dluzsze linki prowadza do sklepow Amazona
    protected String searchForLink(Element element) {
        String link = element.select("strong.cat-prod-row-name a")
                .attr("abs:href");
        if (link.length() < 30) {
            return link;
        }
        return "";
    }

    protected String searchForCategory(Element element) {
        String category = element.select("p.cat-prod-row-category a").text();
        return category;
    }

    protected String searchForTitle(Element element) {
        String title = element.select("strong.cat-prod-row-name").text();
        return title;
    }
}
