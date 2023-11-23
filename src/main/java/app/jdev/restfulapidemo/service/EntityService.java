package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.mapper.Mapper;
import app.jdev.restfulapidemo.model.DTO;
import app.jdev.restfulapidemo.repository.EntityRepository;

public abstract class EntityService<E, ID> {
    
    private final EntityRepository<E, ID> entityRepository;
    private final Mapper<E, ID> mapper;

    public EntityService(EntityRepository<E, ID> entityRepository, Mapper<E, ID> mapper) {
        this.entityRepository = entityRepository;
        this.mapper = mapper;
    }

    public Iterable<E> findAll() {
        return entityRepository.findAll();
    }

    public E findById(ID id) {
        return entityRepository.findById(id).orElseThrow();
    }

    public DTO<ID> save(DTO<ID> entityDTO) {
        E entity = entityRepository.save(mapper.mapToEntity(entityDTO));
        return mapper.mapToDTO(entity);
    }

    public DTO<ID> update(DTO<ID> entityDTO) {
        findById(entityDTO.id());
        return save(entityDTO);
    }

    public void delete(DTO<ID> entityDTO) {
        E entity = findById(entityDTO.id());
        entityRepository.delete(entity);
    }
}
