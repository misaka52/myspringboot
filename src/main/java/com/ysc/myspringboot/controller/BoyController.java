package com.ysc.myspringboot.controller;

import com.ysc.myspringboot.dao.BoyDAO;
import com.ysc.myspringboot.entity.Boy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoyController {
    @Autowired
    BoyDAO boyDAO;

    @RequestMapping(value = "/boys", method = RequestMethod.GET)
    public List<Boy> getBoys() {
        return boyDAO.findAll();
    }

    @RequestMapping(value = "/addBoy", method = RequestMethod.GET)
    public String addBoy(Boy boy) {
        System.out.println(boy.getName() + "," + boy.getAge());
        boyDAO.save(boy);
        return "OK";
    }

}
