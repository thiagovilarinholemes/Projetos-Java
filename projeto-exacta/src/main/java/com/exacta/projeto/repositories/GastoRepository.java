package com.exacta.projeto.repositories;

import com.exacta.projeto.entities.Gasto;
import com.exacta.projeto.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
}
