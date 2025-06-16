package com.project.crud.entityies;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UsuariosEntity {

	@Id
	private Integer id;
	private String nombre;
    
}
