package dev.schunk.simplespringapp.tryout;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TryoutController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "Nikolai") String name) {
        return String.format("Hello %s!", name);
    }
}
