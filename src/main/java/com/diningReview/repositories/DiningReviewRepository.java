package com.diningReview.repositories;

import com.diningReview.model.DiningReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
    List <DiningReview> findByStatus(Boolean status);
    List <DiningReview> findByStatusIsNull();
    List <DiningReview> findByRestaurantIdAndStatusIsTrue(Long restaurantId);
}
