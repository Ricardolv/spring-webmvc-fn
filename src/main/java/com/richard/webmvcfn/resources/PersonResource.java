package com.richard.webmvcfn.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonResource {

    @GetMapping("greet/{name}")
    String greet(@PathVariable String name) {
        return "Hello " + name + "!";
    }
}
