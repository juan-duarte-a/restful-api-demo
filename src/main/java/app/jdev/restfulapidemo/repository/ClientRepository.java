package app.jdev.restfulapidemo.repository;

import org.springframework.data.repository.CrudRepository;

import app.jdev.restfulapidemo.entity.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    
}
