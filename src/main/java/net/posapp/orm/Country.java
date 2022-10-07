package net.posapp.orm;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "COUNTRY")
@Data
@EqualsAndHashCode
public class Country implements Serializable{

	private static final long serialVersionUID = 36541920941744086L;

	@Id
	@Column(name = "ID")
	private Integer id;

	@Column(name = "st_name")
	private String description;

}
