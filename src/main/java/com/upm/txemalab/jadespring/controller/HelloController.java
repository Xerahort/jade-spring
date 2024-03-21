package com.upm.txemalab.jadespring.controller;

import com.upm.txemalab.jadespring.service.AgentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final AgentService aServ;

    public HelloController(AgentService aServ) {
        this.aServ = aServ;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return aServ.salute();
    }

}
