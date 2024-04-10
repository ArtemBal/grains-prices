package org.artem.balan.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.artem.balan.services.PriceCheckService;

@Path("/prices")
public class PriceResource {

    @Inject
    private PriceCheckService priceCheckService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/allPrices")
    public String getPrices() {
        return priceCheckService.getPrices();
    }
}
