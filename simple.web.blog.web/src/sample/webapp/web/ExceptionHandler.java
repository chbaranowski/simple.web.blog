package sample.webapp.web;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import aQute.bnd.annotation.component.Component;

@Component
@Provider
public class ExceptionHandler implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {
		return Response.status(400)
				.header("x-msg", exception.getMessage())
			.build();
	}

}
