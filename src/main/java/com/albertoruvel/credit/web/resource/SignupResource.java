package com.albertoruvel.credit.web.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.albertoruvel.credit.web.data.dto.AuthenticationResult;
import com.albertoruvel.credit.web.data.dto.NewAccountRequest;
import com.albertoruvel.credit.web.service.impl.AccountService;
import com.sun.istack.logging.Logger;

@Path("account")
public class SignupResource {

	private final Logger log = Logger.getLogger(getClass());

	
	@POST
	@Path("signup")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signup(NewAccountRequest request) {
		AuthenticationResult result; 
		//check if user already exists
		if(AccountService.isEmailRegistered(request.getEmail())) {
			result = new AuthenticationResult(false,  "Email is already registered", "");
		}else {
			//register new user 
			String id = AccountService.addAccount(request);
			result = new AuthenticationResult(true, "Signed up successfully", id);
		}
		return Response.ok(result).build();
	}

    @GET
    @Path("signin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signin(){
        return null;
    }
}
