package com.bridgelabz.greetingapp.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public ObjectNode myService(ObjectNode objectNode){
        return objectNode.put("used_service","myservice");
    }
}
