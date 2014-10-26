package sample.webapp.web.tests

import javax.ws.rs.client.Client
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

import simple.web.blog.data.Blog
import simple.web.blog.data.BlogRepository
import osgi.test.spock.*
import spock.lang.*

class BlogResourceSpec extends Specification {

    @OSGiServiceRegistration
    BlogRepository mockBlogRepository
    
    def setup() {
        mockBlogRepository = Mock(BlogRepository)
    }
    
    def getProductsByExistingId() {
        given:
        mockBlogRepository.findOne(42) >> new Blog(title: 'OSGi in Action', content: '-')
        when:
        Client client = ClientBuilder.newClient();
        Response response = client
            .target("http://localhost:8080")
                .path("halres")   
                .path("blog")
                .path("42")
                .request().get();
        then:
        response.status == Status.OK.statusCode
    }        
    
    def getProductRepositoryThrowsRuntimeException() {
        given:
        mockBlogRepository.findOne(_) >> { throw new RuntimeException() }
        when:
        Client client = ClientBuilder.newClient();
        Response response = client
            .target("http://localhost:8080")
                .path("halres")
                .path("blog")
                .path("42")
                .request().get();
        then:
        response.status == Status.BAD_REQUEST.statusCode
    }
    
    def deleteProduct() {
        when:
        Client client = ClientBuilder.newClient();
        Response response = client
            .target("http://localhost:8080")
                .path("halres")
                .path("blog")
                .path("42")
                .request().delete();
        then:
        response.status == Status.OK.statusCode
        and:
        1 * mockBlogRepository.delete(42)
    }
        
}
