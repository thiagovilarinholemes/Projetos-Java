package project.exacta.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import project.exacta.gasto.entity.Tag;

import javax.transaction.Transactional;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    public List<Tag> findByName(String name);

    /** Remove all tags referring to the Gasto */
    @Transactional
    @Modifying
    Long removeByGastoId(Long id);
}
