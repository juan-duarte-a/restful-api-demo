package app.jdev.restfulapidemo.service;

import app.jdev.restfulapidemo.mapper.Mapper;
import app.jdev.restfulapidemo.dto.DTO;
import app.jdev.restfulapidemo.repository.EntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;

public abstract class EntityService<E, ID> {

    private final EntityRepository<E, ID> entityRepository;
    private final Mapper<E, ID> mapper;

    public EntityService(EntityRepository<E, ID> entityRepository, Mapper<E, ID> mapper) {
        this.entityRepository = entityRepository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Iterable<DTO<ID>> findAll() {
        Iterable<E> entityList = entityRepository.findAll();
        LinkedList<DTO<ID>> dtoList = new LinkedList<>();
        entityList.forEach(entity -> dtoList.add(mapper.mapToDTO(entity)));
        return dtoList;
    }

    public DTO<ID> findById(ID id) {
        E entity = entityRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        return mapper.mapToDTO(entity);
    }

    public DTO<ID> save(DTO<ID> entityDTO) {
        E entity = entityRepository.save(mapper.mapToEntity(entityDTO));
        return mapper.mapToDTO(entity);
    }

    public DTO<ID> update(ID id, DTO<ID> entityDTO) {
        entityRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found"));
        E entity = mapper.updateAndMapToEntity(id, entityDTO);
        entity = entityRepository.save(entity);
        return mapper.mapToDTO(entity);
    }

    public void delete(ID id) {
        if (!entityRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity not found");
        }
        entityRepository.deleteById(id);
    }
}
