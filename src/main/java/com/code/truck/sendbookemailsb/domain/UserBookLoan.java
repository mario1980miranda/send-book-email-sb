package com.code.truck.sendbookemailsb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBookLoan {
    private User user;
    private Book book;
    private Date loan_date;
}
