package app.jdev.restfulapidemo.repository;

import app.jdev.restfulapidemo.entity.Client;

public interface ClientRepository extends EntityRepository<Client, Long> {

    boolean existsByNameAndPhoneNumberOrEmail(String name, String phoneNumber, String email);

}
