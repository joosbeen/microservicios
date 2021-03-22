package academy.digitallap.store.serviceshopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.digitallap.store.serviceshopping.entity.Invoice;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    public List<Invoice> findByCustomerId(Long customerId );
    public Invoice findByNumberInvoice(String numberInvoice);
}
