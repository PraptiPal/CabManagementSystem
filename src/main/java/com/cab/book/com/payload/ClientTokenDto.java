package com.cab.book.com.payload;

	import com.fasterxml.jackson.annotation.JsonProperty;
	import lombok.Data;

	@Data
	public class ClientTokenDto {
	    @JsonProperty("client_token")
	    private String clientToken;
	    @JsonProperty("expires_in")
	    private Long expiresIn;
	}

