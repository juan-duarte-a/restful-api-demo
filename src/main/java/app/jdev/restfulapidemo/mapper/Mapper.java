package app.jdev.restfulapidemo.mapper;

import app.jdev.restfulapidemo.dto.DTO;

public interface Mapper<E, ID> {

    E mapToEntity(DTO<ID> entityDTO);
    DTO<ID> mapToDTO(E entity);
    E updateAndMapToEntity(ID id, DTO<ID> entityDTO);
    
}
