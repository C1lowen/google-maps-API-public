package com.codefinity.googlemapsapi.dto;

import com.google.maps.model.Photo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RestaurantResponse {
    private String place_id;
    private String name;
    private double rating;
    private String description;
    private String distance;
    private String photo;
}
