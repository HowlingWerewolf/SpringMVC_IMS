package com.ims.repository.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "PRODUCTS")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_ID_GENERATOR")
	@SequenceGenerator(name = "PRODUCT_ID_GENERATOR", sequenceName = "PRODUCT_SEQ", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false)
    private int id;
	
	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;
	
	@Column(name = "PRICE", unique = true, nullable = false, precision = 5, scale = 0)
    private Double price;

	
    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }
    
    
    public String getDescription() {
        return description;
    }
      
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + ";");
        buffer.append("Price: " + price);
        return buffer.toString();
    }
}
