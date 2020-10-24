package academy.digitallab.serviceshopping.repository;

import academy.digitallab.serviceshopping.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
