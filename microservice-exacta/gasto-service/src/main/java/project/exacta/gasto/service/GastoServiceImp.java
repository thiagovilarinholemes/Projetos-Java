package project.exacta.gasto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.exacta.gasto.entity.Gasto;
import project.exacta.gasto.entity.Tag;
import project.exacta.gasto.repository.GastoRepository;
import project.exacta.gasto.repository.TagRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GastoServiceImp implements GastoService{

    private final GastoRepository gastoRepository;
    private final TagRepository tagRepository;

    /** Service to List All Gastos **/
    @Override
    public List<Gasto> listAllGasto() {
        return gastoRepository.findAll();
    }

    /** Service to List ID Gasto **/
    @Override
    public Gasto getGasto(Long id) {
        return gastoRepository.findById(id).orElse(null);
    }

    /** Service to Create Gasto **/
    @Override
    public Gasto createGasto(Gasto gasto) {
        Gasto gastoDB = new Gasto(null, gasto.getName(), gasto.getDescription(), gasto.getValor());
        gastoDB.setStatus("CREATED");
        gastoDB.setDate(gasto.getDate() != null ? gasto.getDate() : Calendar.getInstance());
        gastoDB.setCreateAt(new Date());
        gastoRepository.save(gastoDB);

        /** Insert Tag */
        for(Tag item : gasto.getTags()){
            Tag tag = new Tag(null, item.getName());
            tagRepository.save(tag);
            gastoDB.getTags().add(tag);
            gastoRepository.save(gastoDB);
            tag.setGasto(gastoDB);
            tagRepository.save(tag);
        }
        return gastoDB;
    }

    /** Service to Update Gastos **/
    @Override
    public Gasto updateGasto(Long id, Gasto obj) {
        try {
            Gasto entity = gastoRepository.getOne(id);

            /** Remove all Tags of Database */
            tagRepository.removeByGastoId(id);

            /** Call Method updateDate */
            updateData(entity, obj);

            return gastoRepository.save(entity);
        }
        catch (EntityNotFoundException e){
            return null;
        }
    }

    /** Service to Delete Gasto **/
    @Override
    public Gasto deleteGasto(Long id) {
        Gasto gastoDB = getGasto(id);
        if (gastoDB == null){
            return null;
        }
        gastoDB.setStatus("DELETED");
        return gastoRepository.save(gastoDB);
    }

    /** Method Insert Update Gasto */
    private void updateData(Gasto entity, Gasto obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setDate(Calendar.getInstance());
        entity.setValor(obj.getValor());
        gastoRepository.save(entity);

        /** Insert Tag */
        for(Tag item : obj.getTags()){
            Tag tag = new Tag(null, item.getName());
            tagRepository.save(tag);
            entity.getTags().add(tag);
            gastoRepository.save(entity);
            tag.setGasto(entity);
            tagRepository.save(tag);
        }
    }
}
