package com.exacta.projeto.repositories;

import com.exacta.projeto.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;


public interface TagRepository extends JpaRepository<Tag, Long>  {

    @Transactional
    @Modifying
    Long removeByGastoId(Long id);
}
