package simple.web.blog.web;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.data.domain.Sort;

import osgi.web.common.Resource;
import simple.web.blog.data.Blog;
import simple.web.blog.data.BlogRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component
@Path("/rest/blog")
public class BlogResource implements Resource {
	
	BlogRepository blog;

	@GET
	public List<Blog> list() {
		return blog.findAll(new Sort(Sort.Direction.DESC, "lastModified"));
	}
	
	@GET
	@Path("/{postId}")
	public Blog get(@PathParam("postId") Long id) {
		return blog.getOne(id);
	}
	
	@POST
	public void post(Blog entry) {
		blog.save(entry);
	}
	
	@DELETE
	@Path("/{postId}")
	public void delete(@PathParam("postId") Long id) {
		blog.delete(id);
	}
	
	@Reference(dynamic = true)
	public void setBlogRepository(BlogRepository blog) {
		this.blog = blog;
	}
	
	public void unsetBlogRepository(BlogRepository blog) {
		this.blog = null;
	}
	
}
