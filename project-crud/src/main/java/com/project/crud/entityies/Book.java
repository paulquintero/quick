package com.project.crud.entityies;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Book")
@Getter
@Setter
public class Book {

	@Id
	private Integer id;
	private String name;
	private String description;
	private String creationDate;
    
}
