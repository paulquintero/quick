package com.project.crud.entityies;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Logbook")
@Getter
@Setter
public class Logbook {

	@Id
	private Integer id;
	private String economicNumber;
	private String vin;
	private String insurancePolicy;
	private String deliveryDate;
	private String payDate;
	private String logbookEndDate;
	private String link;
	private Integer paperworkId;
	private String creationDate;
    
}
