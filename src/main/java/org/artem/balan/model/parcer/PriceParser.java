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

    public ArrayList<Offer> parsePrices(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        ArrayList<Offer> offerList = new ArrayList<>();

        Element table = doc.select("table").get(0).select("tbody").get(0);
        System.out.println(table);
        Elements trs = table.select("tr");
        for (Element row : trs) {
            Elements tds = row.select("td");

            Offer offer = new Offer();
            offer.setCrop("соя");
            offer.setPrice(tds.get(1).text());
            offerList.add(offer);
        }
        return offerList;
    }
}
