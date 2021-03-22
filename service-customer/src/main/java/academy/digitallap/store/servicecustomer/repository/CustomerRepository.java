package academy.digitallap.store.servicecustomer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import academy.digitallap.store.servicecustomer.entity.Customer;
import academy.digitallap.store.servicecustomer.entity.Region;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByNumberID(String numberID);

	public List<Customer> findByLastName(String lastName);

	public List<Customer> findByRegion(Region region);
}
