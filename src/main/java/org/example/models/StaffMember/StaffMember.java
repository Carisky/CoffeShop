package org.example.models.StaffMember;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StaffMember {
    private int id;
    private String fullName;
    private String contactPhone;
    private String contactEmail;
    private String position;


}
