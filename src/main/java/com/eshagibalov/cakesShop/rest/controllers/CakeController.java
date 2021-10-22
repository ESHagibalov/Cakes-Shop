package com.eshagibalov.cakesShop.rest.controllers;

import com.eshagibalov.cakesShop.dto.Cake;
import com.eshagibalov.cakesShop.dto.Cakes;
import com.eshagibalov.cakesShop.exceptions.CakeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
//@RequestMapping("v1/cakes")
public class CakeController {
    private final Cakes cakeList = new Cakes();
    private Long count = 1L;

    public CakeController() {
        Cake cake1 = new Cake();
        cake1.setId(count);
        count++;
        cake1.setName("Napoleon");
        cake1.setCalories(new BigDecimal(250));
        cake1.setPrice(new BigDecimal(100));
        cake1.setWeight(new BigDecimal(150));
        cake1.setImage("cake1.jpeg");

        Cake cake2 = new Cake();
        cake2.setId(count);
        count++;
        cake2.setName("Rose");
        cake2.setCalories(new BigDecimal(200));
        cake2.setPrice(new BigDecimal(130));
        cake2.setWeight(new BigDecimal(100));
        cake2.setImage("cake1.jpeg");

        List<Cake> tmp = new ArrayList<Cake>();
        tmp.add(cake1);
        tmp.add(cake2);

        cakeList.setCakeList(tmp);
    }

    @GetMapping(value = "cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes() {
        return cakeList;
    }

    @GetMapping(value = "cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake cake(@PathVariable Long id) {
        return cakeList.getCakeList().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CakeNotFoundException("Cake not found"));
    }

    @PostMapping(path = "add_cake", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> addCake(@RequestBody Cake cake) {
        if((cake.getName().equals(null) && cake.getWeight().equals(null) &&
                cake.getCalories().equals(null) && cake.getImage().equals(null) && cake.getPrice().equals(null))) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            cake.setId(count);
            count++;
            cakeList.getCakeList().add(cake);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
