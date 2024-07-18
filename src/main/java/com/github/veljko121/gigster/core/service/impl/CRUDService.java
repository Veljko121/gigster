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
        return mapToResponseDTO(findByIdDomain(id));
    }

    protected T findByIdDomain(ID id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow();
    }

    public Collection<TResponseDTO> findAll() {
        return mapToResponseDTOs(findAllDomain());
    }

    protected Collection<T> findAllDomain() {
        return repository.findAll();
    } 

    public Collection<TResponseDTO> findAllByIds(Iterable<ID> ids) {
        return mapToResponseDTOs(findAllByIdsDomain(ids));
    }

    public Collection<T> findAllByIdsDomain(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    public TResponseDTO save(TRequestDTO requestDTO) {
        return mapToResponseDTO(saveDomain(requestDTO));
    }

    protected T saveDomain(TRequestDTO requestDTO) {
        return repository.save(mapToDomain(requestDTO));
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public TResponseDTO update(ID id, TUpdateRequestDTO updatedEntityRequestDTO) {
        return mapToResponseDTO(updateDomain(id, updatedEntityRequestDTO));
    }

    public T updateDomain(ID id, TUpdateRequestDTO updatedEntityRequestDTO) {
        return mapUpdatedFieldsToDomain(findByIdDomain(id), updatedEntityRequestDTO);
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
