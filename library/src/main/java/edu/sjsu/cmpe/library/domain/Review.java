package edu.sjsu.cmpe.library.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
	
	private long id ;//# You will generate this key.
	@NotNull
	@JsonProperty(value="rating", required=true)
	private Integer rating ;//(1-5 stars) (Required field)
	@NotEmpty
	@JsonProperty(value="comment", required=true)
	private String comment;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	

}
