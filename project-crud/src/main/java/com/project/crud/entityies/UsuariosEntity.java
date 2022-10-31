package com.project.crud.entityies;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
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
