package com.exacta.projeto.services;

import com.exacta.projeto.entities.Gasto;
import com.exacta.projeto.entities.Tag;
import com.exacta.projeto.repositories.GastoRepository;
import com.exacta.projeto.repositories.TagRepository;
import com.exacta.projeto.services.exceptions.DatabaseException;
import com.exacta.projeto.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.*;

@Service
public class GastoService {

    @Autowired
    private GastoRepository repository;
    @Autowired
    private TagRepository tagRepository;


    public List<Gasto> findAll(){
        return repository.findAll();
    }

    public Gasto findById(Long id){
        Optional<Gasto> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Gasto insert(Gasto obj){
        Gasto gasto = new Gasto(null, obj.getNomePessoa(), obj.getDescricao(), obj.getDataHora(), obj.getValor());
        for(Object item : obj.getTags()){
            Tag tag = new Tag(null, item.toString());
            tagRepository.save(tag);
            gasto.getTags().add(tag);
            repository.save(gasto);
            tag.setGasto(gasto);
            tagRepository.save(tag);
        }
        return repository.save(gasto);
    }

    public void delete(Long id){
        try {
            tagRepository.removeByGastoId(id);
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Gasto update(Long id, Gasto obj){
        try {
            Gasto entity = repository.getOne(id);
            tagRepository.removeByGastoId(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Gasto entity, Gasto obj) {
        entity.setNomePessoa(obj.getNomePessoa());
        entity.setDescricao(obj.getDescricao());
        entity.setValor(obj.getValor());
        for(Object item : obj.getTags()){
            Tag tag = new Tag(null, item.toString());
            tagRepository.save(tag);
            entity.getTags().add(tag);
            repository.save(entity);
            tag.setGasto(entity);
            tagRepository.save(tag);
        }
    }
}
