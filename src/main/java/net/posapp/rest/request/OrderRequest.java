package net.posapp.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest {

	private Integer orderId;

	private Integer customerId;
	
	private String status;

}
