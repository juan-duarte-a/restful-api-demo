package app.jdev.restfulapidemo.mapper;

import app.jdev.restfulapidemo.model.DTO;

public interface Mapper<E, ID> {

    E mapToEntity(DTO<ID> entityDTO);
    DTO<ID> mapToDTO(E entity);
    
}
