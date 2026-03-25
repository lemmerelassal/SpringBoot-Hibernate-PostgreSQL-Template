package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello(
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "length", defaultValue = "11") int length) {
        
        String text = "Hello world";
        
        // Validate parameters
        if (offset < 0 || length < 0 || offset >= text.length()) {
            return "";
        }
        
        // Calculate the end index
        int endIndex = Math.min(offset + length, text.length());
        
        return text.substring(offset, endIndex);
    }
}
