package org.example.models.MenuItem;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class MenuItem {
    private int id;
    private String Type;
    private BigDecimal Price;
    private String Name;
}

