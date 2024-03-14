package org.example.models.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String fullName;
    private Date birthDate;
    private String contactPhone;
    private String contactEmail;
    private BigDecimal discount;
}

