package com.codefinity.googlemapsapi.dto;

import com.google.maps.PlaceDetailsRequest;
import com.google.maps.model.OpeningHours;
import com.google.maps.model.Photo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestaurantDetailsResponse {
    private String name;
    private double rating;
    private String description;
    private String address;
    private String website;
    private String distance;
    private OpeningHours opening_hours;
    private List<String> photos;
}