package com.cab.book.com.payload;

	import com.fasterxml.jackson.annotation.JsonProperty;
	import lombok.Data;

	import java.io.Serializable;
	import java.util.List;

	@Data
	public class OrderDto implements Serializable {
	    private OrderIntent intent;
	    @JsonProperty("purchase_units")
	    private List<PurchaseUnit> purchaseUnits;
	    @JsonProperty("application_context")
	    private PayPalAppContextDto applicationContext;
	    private Integer userId;
	}
	
