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
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String displayName;
    private String city;
    private String state;
    private Integer zipcode;
    private Boolean isPeanutAllergies;
    private Boolean isEggAllergies;
    private Boolean isDairyAllergies;
}
