package com.codefinity.googlemapsapi.service;

import com.codefinity.googlemapsapi.exceptions.PhotoServiceException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@Log4j2
public class PhotoService {

    @Value("${google.api.key}")
    private String apiKey;

    public String getPhotoUrl(String photoReference) {
        try {
            String url = buildPhotoUrl(photoReference);
            return fetchRedirectLocation(url);
        } catch (Exception e) {
            throw new PhotoServiceException("Failed to fetch direct image URL for reference: " + photoReference);
        }
    }

    public List<String> getPhotoUrls(List<String> photoReferences) {
        List<String> urls = new ArrayList<>();
        for (String reference : photoReferences) {
            try {
                String url = buildPhotoUrl(reference);
                String imageUrl = fetchRedirectLocation(url);
                if (imageUrl != null) {
                    urls.add(imageUrl);
                }
            } catch (Exception e) {
                log.info("Error fetching photo for reference: {}", reference);
                throw new PhotoServiceException("Error fetching photo for reference");
            }
        }
        return urls;
    }

    private String buildPhotoUrl(String photoReference) {
        return String.format(
                "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800&photo_reference=%s&key=%s",
                photoReference,
                apiKey
        );
    }

    private String fetchRedirectLocation(String urlString) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlString).openConnection();
        conn.setInstanceFollowRedirects(false);
        return conn.getHeaderField("Location");
    }
}
