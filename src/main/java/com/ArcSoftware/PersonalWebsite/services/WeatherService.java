package com.ArcSoftware.PersonalWebsite.services;

import com.ArcSoftware.PersonalWebsite.models.WeatherData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        HttpEntity<WeatherData> data = new HttpEntity<>(new WeatherData(), headers);

        HttpEntity<WeatherData> response = template.exchange("https://api.darksky.net/forecast/d4de52a78e006795bc3f" +
                "009e037fceb9/35.2271,80.8431", HttpMethod.GET, data, WeatherData.class);

        return response.getBody();
    }
}
