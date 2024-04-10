package org.artem.balan.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.artem.balan.model.Offer;
import org.artem.balan.model.parcer.PriceParser;

import java.io.IOException;
import java.util.ArrayList;

@ApplicationScoped
public class PriceCheckService {

    @Inject
    private PriceParser parser;

    private String siteUrl = "https://agrotender.com.ua/traders/region_vinnica/soya";

    public String getPrices() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ArrayList<Offer> offers = parser.parsePrices(siteUrl);
            return objectMapper.writeValueAsString(offers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
