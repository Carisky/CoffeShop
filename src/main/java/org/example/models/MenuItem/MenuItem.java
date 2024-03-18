package org.example.models.MenuItem;

import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MenuItem {
    private int id;
    private String Type;
    private BigDecimal Price;
    private String Name;
}

