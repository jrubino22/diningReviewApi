package com.diningReview.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String displayName;
    @Setter
    private String city;
    @Setter
    private String state;
    @Setter
    private Integer zipcode;
    @Setter
    private Boolean isPeanutAllergies;
    @Setter
    private Boolean isEggAllergies;
    @Setter
    private Boolean isDairyAllergies;
}
