package sample.webapp.web;

import static osgi.persistence.common.Persistence.*;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.server.mvc.Template;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import osgi.web.common.Controller;
import osgi.web.common.Requieres;
import simple.web.blog.data.Blog;
import simple.web.blog.data.BlogRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Requieres.Bootstrap
@Component
@Path("/blog")
public class BlogController implements Controller {
    
    BlogRepository blogRepository;

    @Reference(target = isTransactionalService)
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
    @GET
    @Produces( MediaType.TEXT_HTML )
    @Template(name="list.hbs")
    public  Page<Blog> list(
            @QueryParam("page")      @DefaultValue("0")     Integer page, 
            @QueryParam("size")      @DefaultValue("10")    Integer size) {
        return blogRepository.findAll(new PageRequest(page, size));
    }
    
    @GET
    @Path("/create")
    @Produces( MediaType.TEXT_HTML )
    @Template(name="post.hbs")
    public Blog create() {
        return new Blog();
    }
    
    @GET
    @Path("/{id}/edit")
    @Produces( MediaType.TEXT_HTML )
    @Template(name="post.hbs")
    public Blog update(@PathParam("id") Long id) {
        return blogRepository.getOne(id);
    }
    
    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveAction(@BeanParam BlogPost blogPost) {
        Blog blog = map(blogPost);
    	blogRepository.save(blog);
        return Response.seeOther(UriBuilder.fromPath("/blog").build()).build();
    }

    @GET
    @Path("/{id}/delete")
    @Produces( MediaType.TEXT_HTML )
    public Response deleteAction(@PathParam("id") Long id) {
        blogRepository.delete(id);
        return Response.seeOther(UriBuilder.fromPath("/blog").build()).build();
    }
    
	static Blog map(BlogPost blogPost) {
		Blog blog = new Blog();
        blog.id = blogPost.id;
        blog.title = blogPost.title;
        blog.content = blogPost.content;
		return blog;
	}
    
}
