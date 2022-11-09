package com.project.crud.entityies;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
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
