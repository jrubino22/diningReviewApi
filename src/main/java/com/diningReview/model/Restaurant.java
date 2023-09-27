package com.diningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;
    private Integer restaurantScore;

}
