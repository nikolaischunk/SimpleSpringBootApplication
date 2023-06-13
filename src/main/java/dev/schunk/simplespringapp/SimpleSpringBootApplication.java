package dev.schunk.simplespringapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class SimpleSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleSpringBootApplication.class, args);
    }

    @GetMapping("/api/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "Nikolai") String name) {
        return String.format("Hello %s!", name);
    }

}
