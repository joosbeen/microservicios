package academy.digitallap.store.serviceshopping.service;

import academy.digitallap.store.serviceshopping.client.CustomerClient;
import academy.digitallap.store.serviceshopping.client.ProductClient;
import academy.digitallap.store.serviceshopping.entity.Invoice;
import academy.digitallap.store.serviceshopping.entity.InvoiceItem;
import academy.digitallap.store.serviceshopping.model.Customer;
import academy.digitallap.store.serviceshopping.model.Product;
import academy.digitallap.store.serviceshopping.repository.InvoiceItemsRepository;
import academy.digitallap.store.serviceshopping.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceItemsRepository invoiceItemsRepository;

	@Autowired
	private CustomerClient customerClient;

	@Autowired
	private ProductClient productClient;

	@Override
	public List<Invoice> findInvoiceAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
		if (invoiceDB != null) {
			return invoiceDB;
		}
		invoice.setState("CREATED");
		invoice = invoiceRepository.save(invoice);
		invoice.getItems().forEach(items -> {
			productClient.updateStockProduct(items.getProductId(), items.getQuantity() * -1);
		});
		return invoice;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		Invoice invoiceDB = getInvoice(invoice.getId());
		if (invoiceDB == null) {
			return null;
		}
		invoiceDB.setCustomerId(invoice.getCustomerId());
		invoiceDB.setDescription(invoice.getDescription());
		invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
		invoiceDB.getItems().clear();
		invoiceDB.setItems(invoice.getItems());
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		Invoice invoiceDB = getInvoice(invoice.getId());
		if (invoiceDB == null) {
			return null;
		}
		invoiceDB.setState("DELETED");
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice getInvoice(Long id) {
		
		Invoice invoice = invoiceRepository.findById(id).orElse(null);
		
		if (invoice != null) {
			Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
			invoice.setCustomer(customer);
			
			List<InvoiceItem> listItem = invoice.getItems().stream().map(invoiceItem  -> {
				
				Product product = productClient.productById(invoiceItem.getProductId()).getBody();
				
				invoiceItem .setProduct(product);
				
				return invoiceItem;
				
				
			}).collect(Collectors.toList());
			
			invoice.setItems(listItem);
			
		}
		
		return invoice;
	}
}
