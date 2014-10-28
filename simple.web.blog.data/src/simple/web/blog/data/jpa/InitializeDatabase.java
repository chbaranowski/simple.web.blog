package simple.web.blog.data.jpa;

import static osgi.persistence.common.Persistence.*;

import java.util.List;

import simple.web.blog.data.Blog;
import simple.web.blog.data.BlogRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Reference;

@Component
public class InitializeDatabase {
   
	BlogRepository blogRepository;
	
    @Activate
    public void init() {
    	Blog osgiBlog = new Blog();
    	osgiBlog.title = "OSGi Web Development at the EclipseCon 2014";
    	osgiBlog.content = "Some Spring Data added content here...";
    	blogRepository.save(osgiBlog);
    	
    	List<Blog> osgiBlogPosts = blogRepository.findByTitleContaining("OSGi");
    	System.out.println(osgiBlogPosts);
    }
    
    @Reference(target = isTransactionalService)
	public void setBlogRepository(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
    
}
