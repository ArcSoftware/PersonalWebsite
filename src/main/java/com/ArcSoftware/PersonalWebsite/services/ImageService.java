package com.ArcSoftware.PersonalWebsite.services;

import com.ArcSoftware.PersonalWebsite.models.ImgurData;
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
public class ImageService {
    RestTemplate template;

    public ImageService(RestTemplate template) {
        this.template = template;
    }

    public ImgurData getData() {

        HttpHeaders headers = new HttpHeaders();
        List<String> authheaders = new ArrayList<>();
        authheaders.add("Client-ID acbd2ea05d245d6");
        headers.put("authorization", authheaders);
        HttpEntity<ImgurData> data = new HttpEntity<>(new ImgurData(), headers);
        HttpEntity<ImgurData> response = template.exchange("https://api.imgur.com/3/album/OqVcK/images",
                HttpMethod.GET, data, ImgurData.class);

        return response.getBody();
    }
}
