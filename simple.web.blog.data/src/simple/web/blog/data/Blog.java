package simple.web.blog.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import aQute.bnd.annotation.ProviderType;

@ProviderType
@Entity
public class Blog {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    public Long id;
    
    public String title;
    
    public String content;
    
}
