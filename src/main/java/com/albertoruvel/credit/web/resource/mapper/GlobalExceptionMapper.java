package com.albertoruvel.credit.web.resource.mapper;

import com.albertoruvel.credit.web.data.dto.resp.ExceptionData;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm ss");
    @Override
    public Response toResponse(Exception e) {
        logger.severe(e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ExceptionData("There has been an error processing your request, try again", dateFormat.format(new Date())))
                .build();
    }
}
