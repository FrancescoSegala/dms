package it.eng.snam.summer.dmsmisuraservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.eng.snam.summer.dmsmisuraservice.model.Remi;
import it.eng.snam.summer.dmsmisuraservice.service.summer.Summer;

@RestController
public class RemiController {


    @Autowired
    Summer summer ;

     @GetMapping("/remi/{id}")
    public Remi get( @PathVariable String id){
        return summer.get(id);
    }
}
