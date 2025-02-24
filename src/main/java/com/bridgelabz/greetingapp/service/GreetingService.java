package com.bridgelabz.greetingapp.service;

import com.bridgelabz.greetingapp.model.Greeting;
import com.bridgelabz.greetingapp.repository.GreetingRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GreetingService {

    @Autowired
    GreetingRepository greetingRepository;

    // to find message by id
   public Greeting getGreetById(Long id){
       Optional<Greeting> greetingOptional = greetingRepository.findById(id);
       return greetingOptional.orElseThrow(()->new RuntimeException("Greeting not found by this id"));
   }
   // if id not found anywhere
    public Greeting getGreetingById(Long id) {
        return greetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Greeting not found with id: " + id));
    }

    // list of all greeting messages
    public List<Greeting> getAllGreetings(){
       return greetingRepository.findAll();
    }

    // method for update from database
    public Greeting updateMessage(Long id, String message){
       Optional<Greeting> greetingOptional = greetingRepository.findById(id);

       if(greetingOptional.isPresent()){
           Greeting greeting = greetingOptional.get();
           greeting.setMessage(message);
           return greetingRepository.save(greeting);
       }
       return null;
    }
}
