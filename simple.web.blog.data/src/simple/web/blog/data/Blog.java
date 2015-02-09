package simple.web.blog.data;

import javax.persistence.*;
import aQute.bnd.annotation.ProviderType;

@ProviderType
@Entity
public class Blog {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    public Long id;
    
    @Column(length=255)
    public String title;

    @Lob
    public String content;
    
}
