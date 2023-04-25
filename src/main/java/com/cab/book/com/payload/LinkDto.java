package com.cab.book.com.payload;

import lombok.Data;

@Data
public class LinkDto {
    private String href;
    private String rel;
    private String method;
}
