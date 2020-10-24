package project.exacta.gasto.service;

import org.springframework.stereotype.Service;
import project.exacta.gasto.entity.Gasto;
import project.exacta.gasto.entity.Tag;

import java.util.List;

@Service
public interface TagService {

    public List<Tag> listAllTag();
    public Tag getTag(Long id);
    public Tag createTag(Tag tag);
    public Tag updateTag(Tag tag);
    public Tag deleteTag(Long id);
    public List<Tag> getTagName(String name);
}
