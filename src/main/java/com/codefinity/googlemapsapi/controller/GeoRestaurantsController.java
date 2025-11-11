package com.codefinity.googlemapsapi.controller;

import com.codefinity.googlemapsapi.dto.RestaurantDetailsResponse;
import com.codefinity.googlemapsapi.dto.RestaurantResponse;
import com.codefinity.googlemapsapi.dto.RestaurantSearchRequest;
import com.codefinity.googlemapsapi.service.GeoRestaurantsService;
import com.codefinity.googlemapsapi.service.GooglePlacesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places/restaurants")
@AllArgsConstructor
@Log4j2
public class GeoRestaurantsController {

    private GeoRestaurantsService geoRestaurantsService;

    @GetMapping()
    public List<RestaurantResponse> getRestaurants(
            @Valid @ModelAttribute RestaurantSearchRequest request
    ) {
        List<RestaurantResponse> nearbyRestaurants = geoRestaurantsService.getNearbyRestaurants(request.getLat(), request.getLng(), request.getRadius());
        log.info("SUCCESS - " + nearbyRestaurants.toString());
        return nearbyRestaurants;
    }

    @GetMapping("/{id}")
    public RestaurantDetailsResponse getRestaurants(
            @PathVariable String id,
            @RequestParam double lat,
            @RequestParam double lng
    ) {
        RestaurantDetailsResponse restaurantDetails = geoRestaurantsService.getRestaurantDetails(id, lat, lng);
        log.info("SUCCESS DETAILS - " + restaurantDetails.toString());
        return restaurantDetails;
    }
}
