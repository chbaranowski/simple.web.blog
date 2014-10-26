package simple.web.blog.data.jpa;

import java.util.List;

import simple.web.blog.data.Blog;
import simple.web.blog.data.BlogRepository;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Reference;

@Component
public class InitializeDatabase {
   
	private BlogRepository blogRepository;
	
    @Activate
    public void init() {
    	Blog osgiBlog = new Blog();
    	osgiBlog.setTitle("OSGi Web Development at the EclipseCon 2014");
    	osgiBlog.setContent("Some Spring Data added content here...");
    	blogRepository.save(osgiBlog);
    	
    	List<Blog> osgiBlogPosts = blogRepository.findByTitleContaining("OSGi");
    	System.out.println(osgiBlogPosts);
    }
    
    @Reference(dynamic = true)
	public void setBlogRepository(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
    
    public void unsetBlogRepository(BlogRepository blogRepository) {
		this.blogRepository = null;
	}
    
    
}
