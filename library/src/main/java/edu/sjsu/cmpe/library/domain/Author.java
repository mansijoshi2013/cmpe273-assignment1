package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Author {
	private long id ;
	@NotEmpty
	@JsonProperty(value="name", required=true)
	private String name;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
