package net.posapp.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SCHEDULE_DELIVERY")
public class ScheduleDelivery implements Serializable {

	private static final long serialVersionUID = 3665015246816995876L;

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "id_order", referencedColumnName = "id")
	private Order order;
	
	@OneToOne
	@JoinColumn(name = "id_employee")
	private Employee employee;
	
	@Column(name = "dt_delivery")
	private Date deliveryDate;
	
}
