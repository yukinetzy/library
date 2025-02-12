package kz.aitu.restpro.restpro.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {
    @GetMapping("/main")
    public String myListener(){
        return "Hello World";
    }
}