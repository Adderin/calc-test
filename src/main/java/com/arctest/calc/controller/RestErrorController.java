package com.arctest.calc.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
public class RestErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        log.trace("Not known request occurred");
        return "forward:/";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}