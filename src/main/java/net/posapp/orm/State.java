package net.posapp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "STATE")
@Data
@EqualsAndHashCode
public class State implements Serializable {

	private static final long serialVersionUID = -8582020895349697152L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "st_name")
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_country", referencedColumnName = "id")
	private Country country;
}
