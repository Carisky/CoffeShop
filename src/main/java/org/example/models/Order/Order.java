package org.example.models.Order;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {
    private int id;
    private int customerId;
    private int staffId;
    private int menuId;
    private Timestamp orderDate;

}

