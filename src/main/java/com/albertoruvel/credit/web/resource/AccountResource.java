package com.albertoruvel.credit.web.resource;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.albertoruvel.credit.web.data.dto.req.AccountSigninRequest;
import com.albertoruvel.credit.web.data.dto.req.NewAccountRequest;
import com.albertoruvel.credit.web.service.AccountService;
import com.albertoruvel.credit.web.service.core.Secured;

@Path("account")
public class AccountResource {

    @Inject
	private AccountService accountService;

	@POST
	@Path("signup")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response signup(NewAccountRequest request) throws Exception {
		return accountService.addAccount(request);
	}

    @POST
    @Path("signin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signin(AccountSigninRequest request) throws Exception{
        return accountService.signin(request);
    }

    @GET
    @Path("config")
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getUserConfiguration(@HeaderParam("Authorization")String token) throws Exception{
        return accountService.userConfiguration(token);
    }
}
