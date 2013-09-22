package edu.sjsu.cmpe.library.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
	
	private long id ;//# You will generate this key.
	@JsonProperty(value="rating", required=true)
	private int rating ;//(1-5 stars) (Required field)
	@JsonProperty(value="comment", required=true)
	private String comment;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	

}
