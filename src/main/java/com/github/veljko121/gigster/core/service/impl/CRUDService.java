package com.github.veljko121.gigster.core.service.impl;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.veljko121.gigster.core.service.ICRUDService;

public abstract class CRUDService<T, TRequestDTO, TResponseDTO, TUpdateRequestDTO, ID> implements ICRUDService<T, TRequestDTO, TResponseDTO, TUpdateRequestDTO, ID> {

    private JpaRepository<T, ID> repository;

    public CRUDService(JpaRepository<T, ID> repository) {
        super();
        this.repository = repository;
    }

    public TResponseDTO findById(ID id) throws NoSuchElementException {
        var entity = repository.findById(id).orElseThrow();
        var responseDTO = mapToResponseDTO(entity);
        return responseDTO;
    }

    public Collection<TResponseDTO> findAll() {
        var entities = repository.findAll();
        var responseDTOs = mapToResponseDTOs(entities);
        return responseDTOs;
    }

    public Collection<TResponseDTO> findAllByIds(Iterable<ID> ids) {
        var entities = repository.findAllById(ids);
        var responseDTOs = mapToResponseDTOs(entities);
        return responseDTOs;
    }

    public TResponseDTO save(TRequestDTO requestDTO) {
        var entity = mapToDomain(requestDTO);
        var savedEntity = repository.save(entity);
        var responseDTO = mapToResponseDTO(savedEntity);
        return responseDTO;
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public TResponseDTO update(ID id, TUpdateRequestDTO updatedEntityRequestDTO) {
        var entity = repository.findById(id).orElseThrow();
        var updatedEntity = mapUpdatedFieldsToDomain(entity, updatedEntityRequestDTO);
        var savedUpdatedEntity = repository.save(updatedEntity);
        var responseDTO = mapToResponseDTO(savedUpdatedEntity);
        return responseDTO;
    }

    protected abstract TResponseDTO mapToResponseDTO(T entity);

    protected abstract T mapToDomain(TRequestDTO requestDTO);

    protected abstract T mapUpdatedFieldsToDomain(T entity, TUpdateRequestDTO updatedEntityRequestDTO);

    protected Collection<TResponseDTO> mapToResponseDTOs(Collection<T> entities) {
        var responseDTOs = entities.stream().map(entity -> mapToResponseDTO(entity)).collect(Collectors.toList());
        return responseDTOs;
    }

    protected Collection<T> mapToDomains(Collection<TRequestDTO> requestDTOs) {
        var entities = requestDTOs.stream().map(requestDTO -> mapToDomain(requestDTO)).collect(Collectors.toList());
        return entities;
    }
    
}
