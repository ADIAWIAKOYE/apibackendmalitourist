package com.example.demosecucume.Entities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class massageError {

    public static ResponseEntity<Object> ErreurResponse(String message, HttpStatus status, Object object){

        Map<String, Object> map= new HashMap<String,Object>();
        map.put("Message",message);
        map.put("Status",status.value());
        map.put("data",object);
        return  new ResponseEntity<Object>(map,status);
    }
}
