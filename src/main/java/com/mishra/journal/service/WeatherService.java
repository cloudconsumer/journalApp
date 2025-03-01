package com.mishra.journal.service;

import com.mishra.journal.api.constants.Placeholders;
import com.mishra.journal.api.response.WeatherResponse;
import com.mishra.journal.cache.JournalAppCache;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private JournalAppCache appCache;

    public WeatherResponse getCurrentWeather(String city) {
        String finalAPI = String.format(appCache.getAppCache().get(Placeholders.WEATHER_API),apiKey,city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}

//  http://api.weatherstack.com/current?access_key=5d55c8b5b8d85a1e6a205b8c909631ed&query=New York