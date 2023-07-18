package com.chegus.customer.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customer")
public class Customer {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id")
	private Integer id;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("customer")
	private List<Product> products =new ArrayList<Product>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer id, String firstName, String lastName, String email, String country, String password,
			List<Product> products) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.password = password;
		this.products = products;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", LastName=" + lastName + ", email=" + email
				+ ", country=" + country + ", password=" + password + ", products=" + products + "]";
	}

	
	
}
