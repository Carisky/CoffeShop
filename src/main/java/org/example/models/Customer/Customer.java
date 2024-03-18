package org.example.models.Customer;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Customer {
    private int id;
    private String fullName;
    private Date birthDate;
    private String contactPhone;
    private String contactEmail;
    private BigDecimal discount;
}

