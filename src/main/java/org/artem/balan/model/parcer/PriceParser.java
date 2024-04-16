package org.artem.balan.model.parcer;

import jakarta.enterprise.context.ApplicationScoped;
import org.artem.balan.model.Offer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

@ApplicationScoped
public class PriceParser {
    private ArrayList<Offer> offers;
    private String siteUrlA = "https://agrotender.com.ua/traders/region_vinnica/soya";
    private String siteUrlB = "https://tripoli.land/ua/analytics/soya";

    public PriceParser() {
        offers = new ArrayList<>();
    }

    public ArrayList<Offer> parsePrices() throws IOException {
        if (offers.isEmpty()) {
            parsePricesA(siteUrlA);
            parsePricesB(siteUrlB);
        }
        return offers;
    }

    // agrotender.com.ua
    public void parsePricesA(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table").get(0).select("tbody").get(0);
        Elements trs = table.select("tr");
        for (Element row : trs) {
            Elements tds = row.select("td");

            Offer offer = new Offer();
            offer.setCrop("соя");
            offer.setPrice(tds.get(1).text());
            offer.setSource("agrotender.com.ua");
            offers.add(offer);
        }
    }

    // tripoli.land
    public void parsePricesB(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Element table = doc.select("table").get(0).select("tbody").get(0);
        Elements trs = table.select("tr");
        Offer offer = new Offer();
        offer.setCrop("соя");
        offer.setPrice(trs.get(7).select("td").get(1).text());
        offer.setSource("tripoli.land");
        offers.add(offer);
    }
}
