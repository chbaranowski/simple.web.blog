package simple.web.blog.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;

@Entity
@Data
public class Blog {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id;
    
    private String title;
    
    private String content;
    
}
