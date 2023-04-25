package com.cab.book.com.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderResponseDto {
    private String id;
    private OrderStatus status;
    private List<LinkDto> links;
}
