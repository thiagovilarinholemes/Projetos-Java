package project.exacta.gasto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.exacta.gasto.entity.Gasto;


@Repository
public interface GastoRepository  extends JpaRepository<Gasto, Long> {

}
