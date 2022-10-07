package net.posapp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@javax.persistence.Entity
@Table(name = "ENTITY_ROLE")
@Data
@EqualsAndHashCode
public class EntityRole implements Serializable {

	private static final long serialVersionUID = -5790308869603468507L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_entity", referencedColumnName = "id")
	private Entity entity;

	@ManyToOne
	@JoinColumn(name = "id_role", referencedColumnName = "id")
	private Role role;
	
}
