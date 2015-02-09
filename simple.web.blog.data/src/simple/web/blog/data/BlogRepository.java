package simple.web.blog.data;

import org.springframework.data.jpa.repository.JpaRepository;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface BlogRepository extends JpaRepository<Blog, Long>{

}
