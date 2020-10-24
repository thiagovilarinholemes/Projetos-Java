package project.exacta.gasto.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.exacta.gasto.entity.Tag;
import project.exacta.gasto.repository.TagRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImp implements TagService{

    private final TagRepository tagRepository;

    /** Service to List All Tag **/
    @Override
    public List<Tag> listAllTag() {
        return tagRepository.findAll();
    }

    /** Service to List ID Tag **/
    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    /** Service to Create Tag **/
    @Override
    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    /** Service to Update Tag **/
    @Override
    public Tag updateTag(Tag tag) {
        Tag tagDB = getTag(tag.getId());
        if (null == tagDB){
            return null;
        }
        tagDB.setName(tag.getName());
        return tagRepository.save(tagDB);
    }

    /** Service to Delete Tag **/
    @Override
    public Tag deleteTag(Long id) {
        Tag tagDB = getTag(id);
        if (tagDB == null){
            return null;
        }
        return tagRepository.save(tagDB);
    }

    /** Service List All Name Tag **/
    @Override
    public List<Tag> getTagName(String name) {
        return tagRepository.findByName(name);
    }

}
