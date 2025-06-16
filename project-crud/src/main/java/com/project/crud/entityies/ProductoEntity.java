package com.project.crud.entityies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ProductoEntity")
@Getter
@Setter
public class ProductoEntity {

	@Id
	private Integer id;
	private Integer piezas;
	private String nombre;
    
}
