package com.github.veljko121.gigster.core.service;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface ICRUDService<T, TRequestDTO, TResponseDTO, ID> {

    TResponseDTO findById(ID id) throws NoSuchElementException;

    Collection<TResponseDTO> findAll();

    Collection<TResponseDTO> findAllByIds(Iterable<ID> ids);

    TResponseDTO save(TRequestDTO reqeustDTO);

    TResponseDTO update(TRequestDTO updatedEntityRequestDTO);
    
    void deleteById(ID id);

}
