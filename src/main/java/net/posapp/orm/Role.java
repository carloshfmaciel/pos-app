package net.posapp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "ROLE")
@Data
@EqualsAndHashCode
public class Role implements Serializable {
	
	private static final long serialVersionUID = 5271112350580189698L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "st_name")
	private String name;
}
