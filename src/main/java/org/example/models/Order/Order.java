package org.example.models.Order;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Order {
    private int id;
    private int customerId;
    private int staffId;
    private int menuId;
    private Date orderDate;

}

