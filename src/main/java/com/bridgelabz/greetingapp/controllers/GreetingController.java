package com.bridgelabz.greetingapp.controllers;

import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.repository.GreetingRepository;
import com.bridgelabz.greetingapp.service.GreetingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// <-----------   temprary code --------->


//import com.bridgelabz.greetingapp.service.GreetingService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//public class GreetingController {
//    ObjectMapper objmapper = new ObjectMapper();
//    ObjectNode node;
//    public GreetingController() {
//        node = objmapper.createObjectNode();
//        node.put("message", "Hello World");
//    }
//
//    @Autowired
//    // service object
//    GreetingService greetingService;
//    // method for get
//    @GetMapping(value = {"hello", "hello/{firstName}", "hello/{firstName}/{lastName}"}, produces = "application/json")
//    public ResponseEntity<ObjectNode> sayHello(
//        @PathVariable(value = "firstName", required = false)Optional<String>firstName,
//        @PathVariable(value = "firstName", required = false)Optional<String>lastName){
//        ObjectNode node = objmapper.createObjectNode();
//        firstName.ifPresent(val -> node.put("firstName",val));
//        lastName.ifPresent(val -> node.put("lastName",val));
//        return ResponseEntity.ok(greetingService.myService(node));
//    }
//
//    // get for all
//    @GetMapping(value = "helloall", produces = "application/json")
//    public ResponseEntity<ObjectNode> helloToAll() {
//        return ResponseEntity.ok(greetingService.myService(node));
//    }
//
//    // method for post
//    @PostMapping(value = "hellopost", produces = "application/json")
//    public ResponseEntity<ObjectNode> helloPost(@RequestBody Map<String, String> newData) {
//       node.put("message", newData.get("message"));
//        return ResponseEntity.ok(greetingService.myService(node));
//    }
//
//    // method for put
//    @PutMapping(value = "helloput", produces = "application/json")
//    public ResponseEntity<ObjectNode> helloPut(@RequestBody Map<String, String> newData) {
//       node.put("message", newData.get("message"));
//        return ResponseEntity.ok(greetingService.myService(node));
//    }
//
//    // method for delete
//    @DeleteMapping(value = "/hello", produces = "application/json")
//    public ResponseEntity<ObjectNode> helloDelete(Map<String, String> newData) {
//        node.remove("name");
//        return ResponseEntity.ok(greetingService.myService(node));
//    }
//}

// <-------------   main code   --------------------->

@RestController
public class GreetingController {
    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping("/greet")
    public String greet(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName",required = false) String lastName
    ){
        String msg;
        // if have both first and last name
        if(firstName!=null && lastName!=null){
            msg = "Hello "+firstName+" "+lastName;
        }
        // for first name
        else if (firstName != null) {
            msg = "Hello, " + firstName + "!";
        }
        // for only last name
        else if (lastName != null) {
            msg = "Hello, Mr./Ms. " + lastName + "!";
        }
        // default case
        else {
            msg = "Hello World!";
        }
        Greeting gr =new Greeting();
        gr.setMessage(msg);
        greetingRepository.save(gr);
        return msg;
    }

    @Autowired
    GreetingService greetingService;
    // for finding message by id
    @GetMapping("/greeting/{id}")
    public Greeting getGreetingById(@PathVariable Long id){
        return greetingService.getGreetById(id);
    }
    // if id not found anywhere in database
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // for showing all greeting message from repository or database
    @GetMapping("/greetings")
    public List<Greeting> getAllGreetings(){
        return greetingService.getAllGreetings();
    }

    // for update message
    @PutMapping("greeting/{id}")
    public Greeting updateMessage(@PathVariable Long id, @RequestBody String newMessage){
        return greetingService.updateMessage(id, newMessage);
    }
}
