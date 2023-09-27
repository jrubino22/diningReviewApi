package com.diningReview.repositories;

import com.diningReview.model.DiningReview
import org.springframework.data.repository.CrudRepository;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long> {
}
