package com.albertoruvel.credit.web.resource.filter;

import com.albertoruvel.credit.web.data.UserAccount;
import com.albertoruvel.credit.web.data.dto.resp.AuthenticationResult;
import com.albertoruvel.credit.web.service.core.Secured;
import com.googlecode.objectify.ObjectifyService;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Logger;

@Provider
@Secured
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();
        //get token
        final String authenticationToken = containerRequestContext.getHeaderString("Authorization");
        if (authenticationToken == null || authenticationToken.isEmpty()) {
            abort(containerRequestContext);
        } else {
            //check if class contains secured annotation, if not, check method annotation
            if (method.getDeclaringClass().isAnnotationPresent(Secured.class)){
                validateToken(authenticationToken, containerRequestContext);
            }else if (method.isAnnotationPresent(Secured.class)){
                validateToken(authenticationToken, containerRequestContext);
            }else {
                //no annotation present... no authentication needed
                return;
            }
        }
    }

    private void validateToken(String token, ContainerRequestContext context){
        List<UserAccount> accounts = ObjectifyService.ofy().load().type(UserAccount.class).list();
        for(UserAccount account : accounts){
            if (account.getToken().equals(token)){
                Logger.getLogger(getClass().getName()).info("Authenticated user with token " + token);
                return;
            }
        }
        abort(context);
    }

    private void abort(ContainerRequestContext context){
        context.abortWith(Response
                .status(Response.Status.UNAUTHORIZED)
                .type(MediaType.APPLICATION_JSON)
                .entity(new AuthenticationResult(false, "Unauthorized", "")).build());
    }
}
