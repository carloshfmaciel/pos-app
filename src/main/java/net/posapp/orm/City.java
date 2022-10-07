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
@Table(name = "CITY")
@Data
@EqualsAndHashCode
public class City  implements Serializable {

	private static final long serialVersionUID = 3658924683423613745L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "st_name")
	private String description;

	@ManyToOne
	@JoinColumn(name = "id_state", referencedColumnName = "id")
	private State state;

}
