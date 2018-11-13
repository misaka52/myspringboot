package com.ysc.myspringboot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @RequestMapping(value = {"hi"}, method = RequestMethod.GET)
    public String say(@RequestParam(value = "name") String name,
                      HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("hello, ").append(name).append("<br>");
        stringBuilder.append("getServletPath():").append(request.getServletPath()).append("<br>");
        stringBuilder.append("getContextPath():").append(request.getContextPath()).append("<br>");
        stringBuilder.append("getRequestURI():").append(request.getRequestURI()).append("<br>");
        stringBuilder.append("getRequestURL():").append(request.getRequestURL()).append("<br>");
        return stringBuilder.toString();
    }

    @RequestMapping(value = {"test/{x}"}, method = RequestMethod.GET)
    public String test(@PathVariable String x) {
        return "test";
    }
}
