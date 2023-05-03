package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @GetMapping("/get-port")
    public String getApplicationPort() {
        System.out.println(System.getProperty("server.port"));
        return System.getProperty("local.server.port");
    }
}
