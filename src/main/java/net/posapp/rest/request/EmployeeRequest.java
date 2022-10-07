package net.posapp.rest.request;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class EmployeeRequest {

	private Integer id;

	private String name;

	private String entityType;

	private EmployeeAddressRequest address;

	private String jobRole;

	private Date admissionDate;

	private Date startPeriodTime;

	private Date endPeriodTime;

	private List<String> roles;

}
