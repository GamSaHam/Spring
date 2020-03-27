package com.example.server.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ServerRestController {

    @GetMapping("/spring-rest/foos/{id}")
    ResponseEntity<?> getFoos(@PathVariable String id) {

        return ResponseEntity.ok().body(new Foo(1L,"Foo"));
    }

    @GetMapping("/spring-rest/foos/object/{id}")
    ResponseEntity<?> getFoosObject(@PathVariable String id) {

        Foo foo = new Foo();
        foo.setId(1L);
        foo.setName("Foo");

        return ResponseEntity.ok().body(foo);
    }

    @GetMapping("/spring-rest/foos")
    ResponseEntity<?> getFooHeader() {

        return ResponseEntity.ok("");
    }


    @PostMapping("/spring-rest/foos")
    ResponseEntity<?> postFoos(@RequestBody Foo foo) {

        System.out.println(foo.getId()+","+foo.getName());
        return ResponseEntity.ok().body(foo);
    }

    @PostMapping("/spring-rest/foos/uri")
    ResponseEntity<?> postFoosUri(@RequestBody Foo foo) {

        System.out.println(foo.toString());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @PostMapping("/spring-rest/foos/object")
    ResponseEntity<Foo> postFoosObject(@RequestBody Foo foo) {

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(foo);
    }

    @PostMapping("/spring-rest/foos/form")
    ResponseEntity<Foo> postFoosForm(@RequestParam MultiValueMap<String, String> params) {

        Foo foo = new Foo();

        System.out.println(params.getClass().getName());

        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).body(foo);
    }

    @PutMapping("/spring-rest/foos/{id}")
    ResponseEntity<Foo> putFoos(@PathVariable String id,@RequestBody Foo foo,@RequestHeader HttpHeaders httpHeaders) {

        System.out.println(id);
        System.out.println(foo);
        System.out.println(httpHeaders);

        return ResponseEntity.ok().header("Server-Content", "Content").body(foo);
    }

    @DeleteMapping("/spring-rest/foos/{id}")
    ResponseEntity<Foo> deleteFoo(@PathVariable String id) {
        System.out.println(id);


        URI selfLink = URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        return ResponseEntity.created(selfLink).build();
    }

    @GetMapping("/spring-rest/foos/timeout")
    ResponseEntity<?> getFooTimeout() throws InterruptedException {

        Thread.sleep(2000);


        return ResponseEntity.ok("");
    }





}
