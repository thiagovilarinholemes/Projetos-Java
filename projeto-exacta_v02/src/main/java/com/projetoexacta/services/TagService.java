package com.projetoexacta.services;

import com.projetoexacta.entities.Tag;
import com.projetoexacta.repositories.TagRepository;
import com.projetoexacta.services.exceptions.DatabaseException;
import com.projetoexacta.services.exceptions.ResourceNotFoundException;
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
    private TagRepository tagRepository;

    /** Method List all Tags */
    public List<Tag> findAll(){
        return tagRepository.findAll();
    }

    /** Method List Tag for ID */
    public Tag findById(Long id){
        Optional<Tag> obj = tagRepository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    /** Method Insert Tag */
    public Tag insert(Tag obj){
        return tagRepository.save(obj);
    }

    /** Method Delete Tag */
    public void delete(Long id){
        try {
            tagRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    /** Method Update Tag */
    public Tag update(Long id, Tag obj){
        try {
            Tag entity = tagRepository.getOne(id);
            updateData(entity, obj);
            return tagRepository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    /** Method Insert Update Gasto */
    private void updateData(Tag entity, Tag obj) {
        entity.setNome(obj.getNome());
    }
}
