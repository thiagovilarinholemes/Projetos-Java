package com.projetoexacta.repositories;

import com.projetoexacta.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;


public interface TagRepository extends JpaRepository<Tag, Long> {

    /** Remove all tags referring to the Gasto */
    @Transactional
    @Modifying
    Long removeByGastoId(Long id);
}
