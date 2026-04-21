package org.arielzarate.interfaces.error;

import org.arielzarate.interfaces.error.exception.CustomException;
import org.arielzarate.interfaces.error.exception.WebClientException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandler.class);

    @Override
    public Response toResponse(Exception exception) {
        LOG.error("Exception occurred: ", exception);

        if (exception instanceof IllegalArgumentException) {
            ApiErrorResponse error = new ApiErrorResponse(
                null,
                "Bad Request",
                Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage(),
                null
            );
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }

        if (exception instanceof jakarta.ws.rs.NotFoundException) {
            ApiErrorResponse error = new ApiErrorResponse(
                null,
                "Resource Not Found",
                Response.Status.NOT_FOUND.getStatusCode(),
                exception.getMessage(),
                null
            );
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }

        if (exception instanceof CustomException) {
            ApiErrorResponse error = new ApiErrorResponse(
                null,
                "Generic Error",
                Response.Status.CONFLICT.getStatusCode(),
                exception.getMessage(),
                null
            );
            return Response.status(Response.Status.CONFLICT).entity(error).build();
        }

        if (exception instanceof WebClientException) {
            ApiErrorResponse error = new ApiErrorResponse(
                null,
                "External API Error",
                Response.Status.BAD_GATEWAY.getStatusCode(),
                exception.getMessage(),
                null
            );
            return Response.status(Response.Status.BAD_GATEWAY).entity(error).build();
        }

        ApiErrorResponse error = new ApiErrorResponse(
            null,
            "Internal Server Error",
            Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
            exception.getMessage(),
            null
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}