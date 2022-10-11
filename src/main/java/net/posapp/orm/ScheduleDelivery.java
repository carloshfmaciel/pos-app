package net.posapp.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "SCHEDULE_DELIVERY")
@Data
@EqualsAndHashCode
public class ScheduleDelivery implements Serializable {

	private static final long serialVersionUID = 3665015246816995876L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "status")
	private String status;

	@Column(name = "dt_delivered_at")
	private Date deliveredAt;

}
