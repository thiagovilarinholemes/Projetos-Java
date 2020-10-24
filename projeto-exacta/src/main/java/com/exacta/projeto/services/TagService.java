package com.exacta.projeto.services;

import com.exacta.projeto.entities.Tag;
import com.exacta.projeto.repositories.TagRepository;
import com.exacta.projeto.services.exceptions.DatabaseException;
import com.exacta.projeto.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository repository;

    public List<Tag> findAll(){
        return repository.findAll();
    }

    public Tag findById(Long id){
        Optional<Tag> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Tag insert(Tag obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public Tag update(Long id, Tag obj){
        try {
            Tag entity = repository.getOne(id);
            updateData(entity, obj);
            return repository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    private void updateData(Tag entity, Tag obj) {
        entity.setNome(obj.getNome());
    }
}
