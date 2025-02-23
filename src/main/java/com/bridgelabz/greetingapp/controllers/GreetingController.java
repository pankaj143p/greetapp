package com.bridgelabz.greetingapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GreetingController {
    ObjectMapper objmapper = new ObjectMapper();
    ObjectNode node;
    public GreetingController() {
        node = objmapper.createObjectNode();
        node.put("message", "Hello World");
    }
    // method for get
    @GetMapping(value = "hello", produces = "application/json")
    public ResponseEntity<ObjectNode> sayHello() {
        return ResponseEntity.ok(node);
    }

    // get for all
    @GetMapping(value = "helloall", produces = "application/json")
    public ResponseEntity<ObjectNode> helloToAll() {
        return ResponseEntity.ok(node);
    }

    // method for post
    @PostMapping(value = "hellopost", produces = "application/json")
    public ResponseEntity<ObjectNode> helloPost(@RequestBody Map<String, String> newData) {
       node.put("message", newData.get("message"));
        return ResponseEntity.ok(node);
    }

    // method for put
    @PutMapping(value = "helloput", produces = "application/json")
    public ResponseEntity<ObjectNode> helloPut(@RequestBody Map<String, String> newData) {
       node.put("message", newData.get("message"));
        return ResponseEntity.ok(node);
    }

    // method for delete
    @DeleteMapping(value = "/hello", produces = "application/json")
    public ResponseEntity<ObjectNode> helloDelete(Map<String, String> newData) {
        return ResponseEntity.ok(new ObjectMapper().createObjectNode().put("message",newData.remove("message")));
    }
}
