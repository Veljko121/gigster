package com.github.veljko121.gigster.core.service;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface ICRUDService<T, TRequestDTO, TResponseDTO, TUpdateRequestDTO, ID> {

    TResponseDTO findById(ID id) throws NoSuchElementException;

    Collection<TResponseDTO> findAll();

    Collection<TResponseDTO> findAllByIds(Collection<ID> ids);

    TResponseDTO save(TRequestDTO reqeustDTO);

    TResponseDTO update(ID id, TUpdateRequestDTO updatedEntityRequestDTO);
    
    void deleteById(ID id);

    void delete(T entity);

    void deleteAll();

}
