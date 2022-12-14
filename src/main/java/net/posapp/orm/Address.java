package net.posapp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ADDRESS")
@Data
@EqualsAndHashCode
public class Address implements Serializable {

	private static final long serialVersionUID = 5021495032796796951L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne(mappedBy = "address")
	private net.posapp.orm.User entity;
	
	@Column(name = "st_address")
	private String address;
	
	@Column(name = "nm_number")
	private Integer number;
	
	@Column(name = "st_zipcode")
	private String zipCode;

	@OneToOne
	@JoinColumn(name = "id_city", referencedColumnName = "id")
	private City city;

}
