package net.posapp.orm;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "EMPLOYEE")
@PrimaryKeyJoinColumn(name = "id")
@Data
public class Employee extends net.posapp.orm.Entity implements Serializable {
	
	private static final long serialVersionUID = -192976203164589761L;

	@Column(name = "job_role")
	private String jobRole;
	
	@Column(name = "dt_admission")
	private Date admissionDate;

	@Column(name = "ts_start_period")
	private LocalTime startPeriodTime;

	@Column(name = "ts_end_period")
	private LocalTime endPeriodTime;
	
}
