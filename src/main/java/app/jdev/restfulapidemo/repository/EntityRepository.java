package app.jdev.restfulapidemo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<E, ID> extends CrudRepository<E, ID> {
    
}
