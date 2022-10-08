package net.posapp.rest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class EmployeeAddressRequest {

	private String address;

	private Integer number;

	private String zipCode;

	private Integer cityId;

}
