package simple.web.blog.data.tests

import org.osgi.framework.BundleContext

import simple.web.blog.data.Blog
import simple.web.blog.data.BlogRepository;
import spock.lang.*
import osgi.test.spock.*

class BlogRepositorySpec extends Specification {

	@OSGiService
	BlogRepository blogRepository
	    
    def setup() {
		blogRepository.deleteAll()
		blogRepository.save(new Blog(title: "OSGi Web Development", content: "-"));
		blogRepository.save(new Blog(title: "Java EE V.S OSGi", content: "-"));
		blogRepository.save(new Blog(title: "Java EE Web Development", content: "-"));
    }
    
    def findByTitleContainingOSGi() {
		when:
		def products = blogRepository.findByTitleContaining(titlePart)
		
		then:
		products.size() == expectedSize
		
		where:
		titlePart || expectedSize
		"OSGi"    || 2
		"V.S"     || 1
    }
        
}
