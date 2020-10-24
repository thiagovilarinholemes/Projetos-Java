package com.projetoexacta.repositories;

import com.projetoexacta.entities.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
}
