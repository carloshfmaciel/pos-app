package net.posapp.rest.request;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ScheduleDeliveryRequest {
	
	private Integer id;

	private Integer orderId;

	private Integer employeeId;

	private Date deliveryDate;

	// A(AGENDADO), D(DELIVERED), C(CANCELED)
	private String status;

	private Date deliveredAt;

}
