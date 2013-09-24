package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import edu.sjsu.cmpe.library.constants.StatusEnum;

public class Book {
	
    private long isbn;
    @NotEmpty
    @JsonProperty(value= "title", required=true)
    private String title;
    @NotEmpty
    @JsonProperty(value="publication-date", required=true)
    private String publicationdate;
    @JsonProperty("language")
    private String language;
    @JsonProperty("num-pages")
    private int numpages;
    @JsonProperty("status")
    private StatusEnum status;
    @JsonProperty("reviews")
    private List<Review> reviews;
    @JsonProperty("authors")
    private List<Author> authors;
    
	public List<Author> getAuthors() {
		if(this.authors == null)
			this.authors = new ArrayList<Author>();
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Review> getReviews() {
		if(this.reviews == null)
			this.reviews = new ArrayList<Review>();
		return reviews;
	}
	
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}	

	/**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }



	/**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
	
	
	
    }
    


	public String getPublicationdate() {
		return publicationdate;
	}

	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getNumpages() {
		return numpages;
	}

	public void setNumpages(int numpages) {
		this.numpages = numpages;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

/*	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	*/
	
	
}
