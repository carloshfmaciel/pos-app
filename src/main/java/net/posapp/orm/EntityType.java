package net.posapp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ENTITY_TYPE")
@Data
@EqualsAndHashCode
public class EntityType implements Serializable {

	private static final long serialVersionUID = -1391845442298107284L;

	@Id
	@Column(name = "st_entity_type")
	private String type;

	@Column(name = "st_name")
	private String description;

}
