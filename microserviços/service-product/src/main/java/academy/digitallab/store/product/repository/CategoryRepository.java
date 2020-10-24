package academy.digitallab.store.product.repository;

import academy.digitallab.store.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {


}
