package mediasoft.education.kvv.cinematograph.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.NoSuchElementException;

/**
 * exception handler for api
 * for cases not able to find entities in db
 */
@Provider
public class NoElementExceptionMapper implements ExceptionMapper<NoSuchElementException> {

    @Override
    public Response toResponse(NoSuchElementException e) {
        ErrorResponse errorResponseEntity = new ErrorResponse(404,"not found", e.getMessage());
        Response errorResponse = Response.status(200).entity(errorResponseEntity).build();
        return errorResponse;
    }
}
