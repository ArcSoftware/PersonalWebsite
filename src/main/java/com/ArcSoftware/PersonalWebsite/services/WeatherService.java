package com.ArcSoftware.PersonalWebsite.services;

import com.ArcSoftware.PersonalWebsite.models.WeatherData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jake on 5/22/17.
 */
@Service
public class WeatherService {
    RestTemplate template;

    public WeatherService(RestTemplate template) {
        this.template = template;
    }

    public WeatherData getWeather() {
        HttpHeaders headers = new HttpHeaders();
        List<String> authheaders = new ArrayList<>();
        authheaders.add(String.format("%s", System.getenv("DSKY_SKEY")));
        headers.put("authorization", authheaders);
        HttpEntity<WeatherData> response = template.exchange("https://api.darksky.net/forecast/{authorization}" +
                "/35.2271,80.8431", HttpMethod.GET, null, WeatherData.class, authheaders);

        return response.getBody();
    }
}
