/**
 * 
 */
package com.shopping.cart.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Srilalitha
 *
 */
@Entity
public class Apparel extends Product {

	private static final long serialVersionUID = 3757339004854428362L;

	@Column(name = "brand")
	private String brand;
	
	@Column(name = "design")
	private String design;

	// constructors
	
	public Apparel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apparel(String brand, String design) {
		super();
		this.brand = brand;
		this.design = design;
	}

	// Getters and setters

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	// toString method
	
	@Override
	public String toString() {
		return "Apparel [brand=" + brand + ", design="
				+ design + "]";
	}

	// equals and hashcode methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((design == null) ? 0 : design.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparel other = (Apparel) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (design == null) {
			if (other.design != null)
				return false;
		} else if (!design.equals(other.design))
			return false;
		return true;
	}	
	
}
