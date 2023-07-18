package com.chegus.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chegus.customer.exception.ResouceNotFoundException;
import com.chegus.customer.model.Customer;
import com.chegus.customer.model.Product;
import com.chegus.customer.repository.CustomerRepository;
import com.chegus.customer.repository.ProductRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		List<Customer> list = customerRepository.findAll();
	     
		return list;
	}
	
	@PostMapping("/customers")
	public void createCustomer(@RequestBody Customer customer) {
		
		if(customer.getId()!=null) {
			Customer customerDB = customerRepository.findById(customer.getId()).orElseThrow(() -> new ResouceNotFoundException("customer not exist with id"+ customer.getId()));
			List<Product> products_DB = customerDB.getProducts();
			List<Product> products_UI = customer.getProducts();
			for(Product p1 : products_DB) {
				boolean flag =false;
				for(Product p2 : products_UI) {
					if(p2.getId()==null || p2.getId().equals(p1.getId())) {
						flag=true;
						break;
					}
				}
				if(flag==false) {
				  productRepository.delete(p1);
				}
			}
			  customerRepository.save(customer);
				 for(Product p : products_UI) {
					 p.setCustomer(customer);
					 productRepository.save(p);
				 } 
			  }else {
		Customer save = customerRepository.save(customer);
		List<Product> products = customer.getProducts();
		if(products!=null) {
			for(Product product : products) {
				product.setCustomer(customer);
				productRepository.save(product);
			}
		}
			  }
	}
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerId(@PathVariable Integer id){
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("customer not exist with id"+ id));
		
		return ResponseEntity.ok(customer);
	}
	
	
	@PutMapping("/customers/{id}")
	public void updatecustomer(@PathVariable(value = "id") Integer id,@RequestBody Customer customerdetails){
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("customer not exist with id"+ id));
	
		if(id!=null) {
		
		List<Product> products_DB = customer.getProducts();
		List<Product> products_UI = customerdetails.getProducts();
		
		for(Product p1 : products_DB) {
			boolean flag =false;
			for(Product p2 : products_UI) {
				if(p2.getId()==null || p2.getId().equals(p1.getId())) {
					flag=true;
					break;
				}
			}
			if(flag==false) {
			  productRepository.delete(p1);
			}
		}
		  customerRepository.save(customerdetails);
			 for(Product p : products_UI) {
				 p.setCustomer(customerdetails);
				 productRepository.save(p);
			 } 
		  }
		}
		
		
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteCustomerById(@PathVariable Integer id){
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("customer not exist with id"+ id));	
		
		customerRepository.delete(customer);
		Map<String, Boolean> hashMap = new HashMap<>();
		hashMap.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(hashMap);
	}
}
