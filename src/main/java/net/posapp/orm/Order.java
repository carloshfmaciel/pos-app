package net.posapp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@javax.persistence.Entity
@Table(name = "ORDERS")
@Data
@EqualsAndHashCode
public class Order implements Serializable {

	private static final long serialVersionUID = 5279496094720245873L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_customer", referencedColumnName = "id")
	private Entity customer;

	@Column(name = "status")
	private String status;
}
