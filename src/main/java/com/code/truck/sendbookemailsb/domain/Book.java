package com.code.truck.sendbookemailsb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String name;
    private String description;
    private String author;
    private String category;
}
