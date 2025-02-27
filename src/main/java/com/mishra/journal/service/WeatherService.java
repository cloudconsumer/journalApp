package com.mishra.journal.service;

import com.mishra.journal.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;

    private static final String apiKey = "5d55c8b5b8d85a1e6a205b8c909631ed";
    private static final String API = "http://api.weatherstack.com/current?access_key=%s&query=%s";

    public WeatherResponse getCurrentWeather(String city) {
        String finalAPI = String.format(API,apiKey,city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}

//  http://api.weatherstack.com/current?access_key=5d55c8b5b8d85a1e6a205b8c909631ed&query=New York