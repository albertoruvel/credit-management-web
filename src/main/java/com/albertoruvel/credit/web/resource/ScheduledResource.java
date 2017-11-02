package com.albertoruvel.credit.web.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@Path("job")
public class ScheduledResource {

    @Path("periods")
    @GET
    public Response checkPeriods(){
        Logger.getLogger(getClass().getCanonicalName()).info("Running cron handler");
        return Response.ok().build();
    }
}
