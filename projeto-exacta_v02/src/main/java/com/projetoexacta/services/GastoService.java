package com.projetoexacta.services;

import com.projetoexacta.entities.Gasto;
import com.projetoexacta.entities.Tag;
import com.projetoexacta.repositories.GastoRepository;
import com.projetoexacta.repositories.TagRepository;
import com.projetoexacta.services.exceptions.DatabaseException;
import com.projetoexacta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;
    @Autowired
    private TagRepository tagRepository;

    /** Method List all Gastos */
    public List<Gasto> findAll(){
        return gastoRepository.findAll();
    }

    /** Method List Gasto for ID */
    public Gasto findById(Long id){
        Optional<Gasto> obj = gastoRepository.findById(id);
        return obj.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    /** Method Insert Gasto */
    public Gasto insert(Gasto obj){
        Gasto gasto = new Gasto(null, obj.getNome(), obj.getDescricao(), obj.getDataHora(), obj.getValor());
        gastoRepository.save(gasto);

        /** Insert Tag */
        for(Object item : obj.getTags()){
            Tag tag = new Tag(null, item.toString());
            tagRepository.save(tag);
            gasto.getTags().add(tag);
            gastoRepository.save(gasto);
            tag.setGasto(gasto);
            tagRepository.save(tag);
        }

        return gasto;
    }

    /** Method Delete Gasto */
    public void delete(Long id){
        try {
            tagRepository.removeByGastoId(id);
            gastoRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    /** Method Update Gasto */
    public Gasto update(Long id, Gasto obj){
        try {
            Gasto entity = gastoRepository.getOne(id);

            /** Remove all Tags of Database */
            tagRepository.removeByGastoId(id);

            /** Call Method updateDate */
            updateData(entity, obj);

            return gastoRepository.save(entity);
        }
        catch (EntityNotFoundException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    /** Method Insert Update Gasto */
    private void updateData(Gasto entity, Gasto obj) {
        entity.setNome(obj.getNome());
        entity.setDescricao(obj.getDescricao());
        entity.setDataHora(Calendar.getInstance());
        entity.setValor(obj.getValor());

        /** Insert Tag */
        for(Object item : obj.getTags()){
            Tag tag = new Tag(null, item.toString());
            tagRepository.save(tag);
            entity.getTags().add(tag);
            gastoRepository.save(entity);
            tag.setGasto(entity);
            tagRepository.save(tag);
        }
    }

}
