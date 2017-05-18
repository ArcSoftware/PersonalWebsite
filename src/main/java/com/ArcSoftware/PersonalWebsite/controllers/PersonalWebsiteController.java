package com.ArcSoftware.PersonalWebsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jake on 5/18/17.
 */

@Controller
public class PersonalWebsiteController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }
    @RequestMapping(path = "/dogs.html", method = RequestMethod.GET)
    public String dogs() {
        return "dogs";
    }



}
