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
public class Book extends Product {
	
	private static final long serialVersionUID = 8040364568190859856L;

	@Column(name = "genre")
	private String genre;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "publications")
	private String publications;

	// Constructors
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String genre, String author, String publications) {
		super();
		this.genre = genre;
		this.author = author;
		this.publications = publications;
	}

	// Getters and setters
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}

	// override toString method
	@Override
	public String toString() {
		return "Book [genre=" + genre + ", author=" + author
				+ ", publications=" + publications + "]";
	}

	// equals and hashcode methods
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result
				+ ((publications == null) ? 0 : publications.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (publications == null) {
			if (other.publications != null)
				return false;
		} else if (!publications.equals(other.publications))
			return false;
		return true;
	}
	
}
