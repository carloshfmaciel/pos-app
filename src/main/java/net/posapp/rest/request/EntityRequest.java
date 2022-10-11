package net.posapp.rest.request;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class EntityRequest {

	private Integer id;

	private String name;

	private String entityType;
	
	private String status;

	private EntityAddressRequest address;

	private List<String> roles;

}
