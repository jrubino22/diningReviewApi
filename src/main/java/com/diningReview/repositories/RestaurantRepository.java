package com.diningReview.repositories;

import com.diningReview.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Optional <Restaurant> findByNameAndZipCode(String name, String zipCode);
    @Query("SELECT r FROM Restaurant r WHERE r.zipCode = :zipCode AND ((r.peanutScore IS NOT NULL AND :allergy = 'peanut') OR (r.eggScore IS NOT NULL AND :allergy = 'egg') OR (r.dairyScore IS NOT NULL AND :allergy = 'dairy'))")
    List<Restaurant> findByZipCodeAndAllergyDesc(@Param("zipCode") String zipCode, @Param("allergy") String allergy);
}
