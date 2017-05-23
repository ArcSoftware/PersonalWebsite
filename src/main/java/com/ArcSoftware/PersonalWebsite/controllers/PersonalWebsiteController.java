package com.ArcSoftware.PersonalWebsite.controllers;

import com.ArcSoftware.PersonalWebsite.models.ImgurData;
import com.ArcSoftware.PersonalWebsite.models.WeatherData;
import com.ArcSoftware.PersonalWebsite.services.ImageService;
import com.ArcSoftware.PersonalWebsite.services.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jake on 5/18/17.
 */

@Controller
public class PersonalWebsiteController {
    ImageService imageService; //autowires our ImgurService into our presentation layer
    WeatherService weatherService;

    public PersonalWebsiteController(ImageService imageService, WeatherService weatherService) {
        this.weatherService = weatherService;
        this.imageService = imageService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("home", true);
        return "index";
    }

    @RequestMapping(path = "/albums", method = RequestMethod.GET)
    public String albums(Model model) {
        ImgurData imageData = imageService.getData("OqVcK");
        model.addAttribute("images", imageData.getImages());
        model.addAttribute("alb", true);
        return "albums";
    }

    @RequestMapping(path = "/soundboard", method = RequestMethod.GET)
    public String homePage(Model model, String name, String version) {
        model.addAttribute("soundname", name);
        model.addAttribute("version", version);
        return "soundboard";
    }

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public String weather(Model model) {
        WeatherData weatherData = weatherService.getWeather();
        model.addAttribute("weather", weatherData.getCurrent());
        return "weather";
    }
    @RequestMapping(path = "/allAlbums", method = RequestMethod.GET)
    public String allAlbums(Model model, String albumID) {
        albumID = (albumID == null) ? "OqVcK" : albumID;
        ImgurData imageData = imageService.getData(albumID);
        model.addAttribute("images", imageData.getImages());
        model.addAttribute("allAlbums", true);
        return "albums";
    }

}
