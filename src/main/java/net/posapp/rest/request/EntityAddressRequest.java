package net.posapp.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntityAddressRequest {

	private String address;

	private Integer number;

	private String zipCode;

	private Integer cityId;

}
