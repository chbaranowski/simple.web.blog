package simple.web.blog.web;

import static osgi.persistence.common.Persistence.*;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import osgi.web.common.Resource;
import simple.web.blog.data.Blog;
import simple.web.blog.data.BlogRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component
@Path("/rest/blog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlogResource implements Resource {
	
	BlogRepository blogRepository;

	@Reference(target = isTransactionalService)
	public void setBlogRepository(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	@GET
	public List<Blog> list() {
		return blogRepository.findAll();
	}
	
	@GET
	@Path("/{postId}")
	public Blog get(@PathParam("postId") Long id) {
		return blogRepository.getOne(id);
	}
	
	@POST
	public void post(Blog blog) {
		blogRepository.save(blog);
	}
	
	@DELETE
	@Path("/{postId}")
	public void delete(@PathParam("postId") Long id) {
		blogRepository.delete(id);
	}
	
}
