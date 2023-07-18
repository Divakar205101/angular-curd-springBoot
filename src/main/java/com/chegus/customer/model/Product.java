package com.chegus.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "product")
public class Product {
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prod_id")
	private Integer id;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "gst")
	private Long gst;
	
	@Column(name = "amount")
	private Long amount;
	
	@ManyToOne
	@JsonIgnoreProperties("customer")
	private Customer customer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getGst() {
		return gst;
	}

	public void setGst(Long gst) {
		this.gst = gst;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(Integer id, String productName, Long price, Long gst, Long amount, Customer customer) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.gst = gst;
		this.amount = amount;
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", price=" + price + ", gst=" + gst + ", amount="
				+ amount + ", customer=" + customer + "]";
	}
	
	
}
