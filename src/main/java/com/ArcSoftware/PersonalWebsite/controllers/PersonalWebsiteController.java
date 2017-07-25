package com.ArcSoftware.PersonalWebsite.controllers;

import com.ArcSoftware.PersonalWebsite.entities.Message;
import com.ArcSoftware.PersonalWebsite.models.ImgurData;
import com.ArcSoftware.PersonalWebsite.models.WeatherData;
import com.ArcSoftware.PersonalWebsite.repositories.MessageRepo;
import com.ArcSoftware.PersonalWebsite.services.ImageService;
import com.ArcSoftware.PersonalWebsite.services.MessageService;
import com.ArcSoftware.PersonalWebsite.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jake on 5/18/17.
 */

@Controller
public class PersonalWebsiteController {
    ImageService imageService;
    WeatherService weatherService;
    MessageService msg;
    MessageRepo messageRepo;

    public PersonalWebsiteController(ImageService imageService, WeatherService weatherService, MessageService msg,
                                     MessageRepo messageRepo) {
        this.weatherService = weatherService;
        this.imageService = imageService;
        this.msg = msg;
        this.messageRepo = messageRepo;
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
        model.addAttribute("weather", weatherData.getCurrently());
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

    @RequestMapping(path = "/chatroom", method = RequestMethod.GET)
    public String chatroom(Model model) {
        List<Message> allMessages= messageRepo.findFirst5ByOrderByIdDesc();
        Collections.reverse(allMessages);
        model.addAttribute("messages", allMessages);
        return "chatroom";
    }
    @RequestMapping(path = "/addmessage", method = RequestMethod.POST)
    public String messagePost(Model model, String message) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = currentAuth.getName();
        msg.createMessage(currentUser, message);
        return "redirect:/chatroom";
    }

    @RequestMapping(path = "/adminchat", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "adminchat";
    }

//    @Autowired
//    DocumentationPluginsBootstrapper documentationPluginsBootstrapper;
//
////    @RequestMapping(path = "/apidoc/", method = RequestMethod.GET)
////    protected String viewMapping(ModelAndView model, Locale locale, WebRequest webRequest, HttpServletRequest request, HttpServletResponse response)
////    {
////        documentationPluginsBootstrapper.stop();
////        documentationPluginsBootstrapper.start();
////
//////        setDefaultObjects(model);
////
////        model.addObject("contextPath", request.getContextPath());
////
////        return "apidoc";
////    }
}
