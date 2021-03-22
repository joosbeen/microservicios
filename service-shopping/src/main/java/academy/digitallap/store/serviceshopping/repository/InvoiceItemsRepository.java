package academy.digitallap.store.serviceshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.digitallap.store.serviceshopping.entity.InvoiceItem;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
