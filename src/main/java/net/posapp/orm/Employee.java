package net.posapp.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "EMPLOYEE")
@Data
@EqualsAndHashCode
public class Employee implements Serializable {
	
	private static final long serialVersionUID = -192976203164589761L;
	
	@Id
	@Column(name = "id_entity")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_entity", referencedColumnName = "id")
	private net.posapp.orm.Entity entity;
	
	@Column(name = "job_role")
	private String jobRole;
	
	@Column(name = "dt_admission")
	private Date admissionDate;

	@Column(name = "ts_start_period")
	private Date startPeriodTime;

	@Column(name = "ts_end_period")
	private Date endPeriodTime;
	
}
