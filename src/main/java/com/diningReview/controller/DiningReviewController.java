package com.diningReview.controller;


import com.diningReview.model.DiningReview;
import com.diningReview.model.Restaurant;
import com.diningReview.model.User;
import com.diningReview.repositories.DiningReviewRepository;
import com.diningReview.repositories.RestaurantRepository;
import com.diningReview.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DiningReviewController {
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private DiningReviewRepository diningReviewRepository;

    public DiningReviewController(RestaurantRepository restaurantRepository, UserRepository userRepository, DiningReviewRepository diningReviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.diningReviewRepository = diningReviewRepository;
    }

    @GetMapping("/all-restaurants")
    public Iterable<Restaurant> returnAllRestaurants() {
        return this.restaurantRepository.findAll();
    }
    @GetMapping("/all-users")
    public Iterable<User> returnAllUsers() {
        return this.userRepository.findAll();
    }
    @GetMapping("/all-reviews")
    public Iterable<DiningReview> returnAllReviews() {
        return this.diningReviewRepository.findAll();
    }
    @GetMapping("/user/{displayName}")
    public User returnUser(@PathVariable String displayName) throws Exception {
        Optional<User> userOptional = this.userRepository.findByDisplayName(displayName);
        if (userOptional.isEmpty()) {
            throw new Exception("User does not exist");
        }
        return userOptional.get();
    }
    @GetMapping("/pending-approval")
    public List<DiningReview> getPendingReviews() {
        return this.diningReviewRepository.findByStatusIsNull();
    }
    @GetMapping("reviews/{restaurantId}")
    public List<DiningReview> getReviewsByRestaurantId(@PathVariable Long restaurantId) {
        return this.diningReviewRepository.findByRestaurantIdAndStatusIsTrue(restaurantId);
    }
    @GetMapping("/restaurants/{zipCode}")
    public List<Restaurant> getRestaurantsByZipCodeAndAllergy(@PathVariable String zipCode, @RequestParam String allergy) {
        return this.restaurantRepository.findByZipCodeAndAllergyDesc(zipCode, allergy);
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User newUser) throws Exception {
        Optional<User> newUserOptional = this.userRepository.findByDisplayName(newUser.getDisplayName());
        if (newUserOptional.isPresent()) {
            throw new Exception("User with display name already exists");
        }
        return this.userRepository.save(newUser);
    }

    @PostMapping("/restaurant")
    public Restaurant createNewRestaurant(@RequestBody Restaurant newRestaurant) throws Exception {
        Optional<Restaurant> newRestaurantOptional = this.restaurantRepository.findByNameAndZipCode(newRestaurant.getName(), newRestaurant.getZipCode());
        if (newRestaurantOptional.isPresent()) {
            throw new Exception("This restaurant already exists in our system");
        }
        return this.restaurantRepository.save(newRestaurant);
    }

    @PostMapping("/dining-review")
    public DiningReview createNewDiningReview(@RequestBody DiningReview diningReview) {
        return this.diningReviewRepository.save(diningReview);
    }

    @PutMapping("/user/{displayName}")
    public User updateUser(@PathVariable String displayName, @RequestBody User user) throws Exception {
        Optional<User> userOptional = this.userRepository.findByDisplayName(displayName);
        if (userOptional.isEmpty()) {
            throw new Exception("User with display name"  + displayName + " does not exist.");
        }
        User foundUser = userOptional.get();
        foundUser.setCity(user.getCity());
        foundUser.setState(user.getState());
        foundUser.setZipcode(user.getZipcode());
        foundUser.setIsPeanutAllergies(user.getIsPeanutAllergies());
        foundUser.setIsEggAllergies(user.getIsEggAllergies());
        foundUser.setIsDairyAllergies(user.getIsDairyAllergies());

        return this.userRepository.save(foundUser);
    }

    @PutMapping("/dining-review/{reviewId}")
    public DiningReview setDiningReviewStatus(@PathVariable Long reviewId, @RequestParam Boolean isApproved) throws Exception {
        Optional<DiningReview> diningReviewOptional = this.diningReviewRepository.findById(reviewId);
        if (diningReviewOptional.isEmpty()) {
            throw new Exception("Review Id could not be found");
        }
        DiningReview foundDiningReview = diningReviewOptional.get();
        foundDiningReview.setStatus(isApproved);
        return foundDiningReview;
    }

}


