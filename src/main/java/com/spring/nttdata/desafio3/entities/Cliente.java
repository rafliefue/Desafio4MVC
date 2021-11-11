package com.spring.nttdata.desafio3.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente {
	
	//Atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ALUMNO")
	private Integer id;
	
	@JoinColumn(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "LAST_NAME", nullable = false, precision = 50)
	private String lastName;
	

	@Column(name = "DNI", nullable = false, precision = 9, unique = true)
	private String dni;
	
	@Column(name = "BIRTH_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	
	

}
