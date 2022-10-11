package net.posapp.rest.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleDeliveryRequest {
	
	private Integer id;

	private Integer orderId;

	private Integer employeeId;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date deliveryDate;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date deliveredAt;

	// A(ACTIVE), I(INACTIVE), D(DELIVERED)
	private String status;
	
}
