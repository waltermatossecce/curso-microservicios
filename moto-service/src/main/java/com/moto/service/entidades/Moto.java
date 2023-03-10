package com.moto.service.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "Moto")
@Table(name = "MOTO")
@Data
public class Moto {

	@Id
	@Column(name="ID")
	@GeneratedValue (strategy = GenerationType.SEQUENCE,generator = "seq_moto")
	@SequenceGenerator(sequenceName = "SEQ_MOTO",allocationSize = 1,name = "seq_moto")
	private int id;
	@Column(name="MARCA")
	private String marca;
	@Column(name="MODELO")
	private String modelo;
	@Column(name="USUARIOID")
	private int usuarioId;
}
