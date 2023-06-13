package dev.schunk.simplespringapp;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleAPIController {

    @GetMapping("/api/example/")
    public String getExample() {
        // Implement the GET request logic here
        return "GET request received";
    }

    @PostMapping("/api/example/")
    public String postExample() {
        // Implement the POST request logic here
        return "POST request received";
    }

    @DeleteMapping("/api/example/")
    public String deleteExample() {
        // Implement the DELETE request logic here
        return "DELETE request received";
    }

}
