package net.posapp.orm;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@javax.persistence.Entity
@Table(name = "ENTITY")
@Data
@EqualsAndHashCode
public class Entity implements Serializable {

	private static final long serialVersionUID = -2329807463128123378L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "st_name")
	private String description;

	@OneToOne
	@JoinColumn(name = "st_entity_type", referencedColumnName = "st_entity_type")
	private EntityType entityType;

	@OneToMany(mappedBy = "entity")
	private List<EntityRole> roles;

}
