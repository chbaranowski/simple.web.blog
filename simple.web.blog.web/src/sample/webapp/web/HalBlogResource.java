package sample.webapp.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static osgi.persistence.common.Persistence.*;
import osgi.web.common.Resource;
import simple.web.blog.data.Blog;
import simple.web.blog.data.BlogRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;

@Component
@Path("/halres/blog")
public class HalBlogResource implements Resource {
    
    RepresentationFactory representationFactory = new JsonRepresentationFactory()
        .withFlag(RepresentationFactory.PRETTY_PRINT);
    
    BlogRepository blogRepository;
    
    @GET
    @Produces({RepresentationFactory.HAL_JSON})
    public Representation list(@Context UriInfo uriInfo, 
            @DefaultValue("0") @QueryParam("page") Integer page) throws URISyntaxException {
        URI requestUri = uriInfo.getRequestUri();
        Representation rep = representationFactory.newRepresentation(requestUri);
        List<Blog> posts = blogRepository.findAll();
        for (Blog blog : posts) {
            URI blogUri = UriBuilder.fromUri(requestUri).path(blog.id.toString()).build();
            Representation representation = representationFactory.newRepresentation(blogUri);
            representation.withBean(blog);
            rep.withRepresentation("posts", representation);
        }
        rep.withProperty("page", page);
        return rep;
    }
    
    @GET
    @Path("/{id}")
    @Produces({RepresentationFactory.HAL_JSON})
    public Representation get(@Context UriInfo uriInfo, @PathParam("id") final Long id){
        Representation rep = representationFactory.newRepresentation(uriInfo.getRequestUri());
        Blog blog = blogRepository.findOne(id);
        if(blog != null) 
        	rep.withBean(blog);
        return rep;
    }
    
    @POST
    @Produces({RepresentationFactory.HAL_JSON})
    public Representation create(@Context UriInfo uriInfo, Blog newBlog) {
        URI requestUri = uriInfo.getRequestUri();
        Representation rep = representationFactory.newRepresentation(requestUri);
        blogRepository.save(newBlog);
        List<Blog> blogs = blogRepository.findAll();
        for (Blog blog : blogs) {
            URI blogUri = UriBuilder.fromUri(requestUri).path(blog.id.toString()).build();
            Representation representation = representationFactory.newRepresentation(blogUri);
            representation.withBean(blog);
            rep.withRepresentation("posts", representation);
        }
        return rep;
    }
    
    @PUT
    @Path("/{id}")
    @Produces({RepresentationFactory.HAL_JSON})
    public Representation update(@Context UriInfo uriInfo, @PathParam("id") Long id, Blog blog) {
    	blog.id = id;
        Blog updatedBlog = blogRepository.save(blog);
        Representation rep = representationFactory.newRepresentation(uriInfo.getRequestUri());
        rep.withBean(updatedBlog);
        return rep;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces({RepresentationFactory.HAL_JSON})
    public Representation delete(@Context UriInfo uriInfo, @PathParam("id") Long id) {
        blogRepository.delete(id);
        Representation rep = representationFactory.newRepresentation(uriInfo.getRequestUri());
        return rep;
    }
    
    @Reference(target = isTransactionalService)
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
}
