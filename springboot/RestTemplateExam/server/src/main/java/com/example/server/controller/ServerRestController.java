package com.example.server.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServerRestController {

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }
    @GetMapping("/hello2")
    ResponseEntity<String> hello2() {
        return ResponseEntity.ok("Hello World!");
    }


    @GetMapping("/age")
    ResponseEntity<String> age(
            @RequestParam("yearOfBirth") int yearOfBirth) {

        int currentYear = 2020;

        if (yearOfBirth > currentYear) {
            return new ResponseEntity<>(
                    "Year of birth cannot be in the future",
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(
                "Your age is " + (currentYear-yearOfBirth+1),
                HttpStatus.OK);
    }
    @GetMapping("/age2")
    ResponseEntity<String> age2(@RequestParam("yearOfBirth") int yearOfBirth) {
        int currentYear = 2020;
        if (yearOfBirth > currentYear) {
            return ResponseEntity.badRequest()
                    .body("Year of birth cannot be in the future");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("Your age is " + (currentYear-yearOfBirth+1));
    }

    @GetMapping("/customHeader")
    ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");

        return new ResponseEntity<>(
                "Custom header set", headers, HttpStatus.OK);
    }
    @GetMapping("/customHeader2")
    ResponseEntity<String> customHeader2() {
        return ResponseEntity.ok().header("Custom-Header", "foo").body("Custom header set");
    }



}
