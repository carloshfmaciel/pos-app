package net.posapp.rest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class OrderRequest {

	private Integer orderId;

	private Integer customerId;
	
	private String status;

}
