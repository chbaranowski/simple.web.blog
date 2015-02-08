package simple.web.blog.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.mvc.Template;

import osgi.web.common.Controller;
import aQute.bnd.annotation.component.Component;

@Requieres.Bootstrap
@Requieres.AngularJS
@Component
@Path("/")
@Produces( MediaType.TEXT_HTML )
public class WebApp implements Controller {

	@GET
    @Template(name="index.hbs")
    public Object get() { return ""; }
	
}
