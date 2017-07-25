package com.ArcSoftware.PersonalWebsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.spring.web.plugins.DocumentationPluginsBootstrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
public class PersonalWebsiteRestController {
    @Autowired
    DocumentationPluginsBootstrapper documentationPluginsBootstrapper;

    @RequestMapping(value = { "/apidoc/" })
    protected ModelAndView viewMapping(ModelAndView model, Locale locale, WebRequest webRequest, HttpServletRequest request, HttpServletResponse response)
    {
        documentationPluginsBootstrapper.stop();
        documentationPluginsBootstrapper.start();

//        setDefaultObjects(model);

        model.addObject("contextPath", request.getContextPath());

        return model;
    }
}
