package com.webmodels.data;

import com.webmodels.model.CeneoProduct;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CeneoProductCollectorTest {

    CeneoProductCollector ceneoProductCollector = new CeneoProductCollector();

    private final String LINK = "https://www.ceneo.pl/;szukaj-dmc-gx80";

    @Test
    public void shouldSayThatTitleIsNotNull() throws Exception {
        Elements elements =  ceneoProductCollector.collectElements(LINK);
        for (Element element : elements
        ) {
            Assert.assertNotNull(ceneoProductCollector.searchForTitle(element));
            System.out.println(ceneoProductCollector.searchForTitle(element));
        }
    }

    @Test
    public void shouldSayThatLinkIsNotNull() throws Exception {
        Elements elements =  ceneoProductCollector.collectElements(LINK);
        for (Element element : elements
        ) {
            Assert.assertNotNull(ceneoProductCollector.searchForLink(element));
            System.out.println(ceneoProductCollector.searchForLink(element));
        }
    }

    @Test
    public void shouldSayThatPriceIsNotNull() throws Exception {
        Elements elements =  ceneoProductCollector.collectElements(LINK);
        for (Element element : elements
        ) {
            Assert.assertNotNull(ceneoProductCollector.searchForPrice(element));
            System.out.println(ceneoProductCollector.searchForPrice(element));
        }
    }

    @Test
    public void shouldSayThatCategoryIsNotNull() throws Exception {
        Elements elements =  ceneoProductCollector.collectElements(LINK);
        for (Element element : elements
        ) {
            Assert.assertNotNull(ceneoProductCollector.searchForCategory(element));
            System.out.println(ceneoProductCollector.searchForCategory(element));
        }
    }

    @Test
    public void shouldSayThatProductIsNotNull() throws Exception {
        List<CeneoProduct> productList = ceneoProductCollector.createProductListFromSymbol("dmc-gx80");
        for (CeneoProduct product: productList
             ) {
            System.out.println(product);

        }
    }



}