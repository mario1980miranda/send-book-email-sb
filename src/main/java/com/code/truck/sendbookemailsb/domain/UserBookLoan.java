package com.code.truck.sendbookemailsb.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBookLoan {
    private User user;
    private Book book;
    private Date loan_date;
}
