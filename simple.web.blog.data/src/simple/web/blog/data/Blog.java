package simple.web.blog.data;

import java.util.Date;

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
    
    @Temporal(TemporalType.TIMESTAMP)
    public Date lastModified;
    
    @Version
    public Long version;
    
    @PrePersist @PreUpdate
    public void updateLastModified() {
    	lastModified = new Date();
    }
    
}
