package com.albertoruvel.credit.web.resource;

import com.albertoruvel.credit.web.data.dto.req.NewCreditCardPurchase;
import com.albertoruvel.credit.web.data.dto.req.NewCreditCardRequest;
import com.albertoruvel.credit.web.service.CreditCardService;
import com.albertoruvel.credit.web.service.core.Secured;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("card")
@Secured
public class CreditCardResource {

    @Inject
    private CreditCardService creditCardService;

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveNewCreditCard(@HeaderParam("Authorization") String token, NewCreditCardRequest request) throws Exception {
        return creditCardService.saveCreditCard(token, request);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCreditCards(@HeaderParam("Authorization") String token) throws Exception {
        return creditCardService.getUserCreditCards(token);
    }

    @GET
    @Path("pie")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreditCardsPieChart(@HeaderParam("Authorization") String token) throws Exception {
        return creditCardService.getPieChartByCreditCards(token);
    }

    @POST
    @Path("purchase/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCreditCardPurchase(@HeaderParam("Authorization") String token, NewCreditCardPurchase request) throws Exception {
        return creditCardService.addNewCreditCardPurchase(token, request);
    }

    @GET
    @Path("{cardId}/purchases")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCreditCardPurchases(@HeaderParam("Authorization") String token, @PathParam("cardId") String cardId) throws Exception{
        return creditCardService.getCreditCardPurchases(token, cardId);
    }

}
