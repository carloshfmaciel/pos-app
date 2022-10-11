package net.posapp.rest.request;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeRequest {

	private Integer id;

	private String name;

	private String entityType;
	
	private String status;

	private EntityAddressRequest address;

	private String jobRole;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date admissionDate;

	@JsonFormat(pattern="HH:mm")
	private LocalTime startPeriodTime;

	@JsonFormat(pattern="HH:mm")
	private LocalTime endPeriodTime;

	private List<String> roles;

}
