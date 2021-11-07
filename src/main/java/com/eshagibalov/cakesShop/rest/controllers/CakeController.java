package com.eshagibalov.cakesShop.rest.controllers;

import com.eshagibalov.cakesShop.goods.CakeServiceImpl;
import com.eshagibalov.cakesShop.rest.dto.Cake;
import com.eshagibalov.cakesShop.rest.dto.Cakes;
import com.eshagibalov.cakesShop.exceptions.CakeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
//@RequestMapping("v1/cakes")
public class CakeController {
    private final Cakes cakeList = new Cakes();
    private Long count = 1L;
    private final CakeServiceImpl cakeService;

    @Autowired
    public CakeController(CakeServiceImpl cakeService) {
        List<Cake> tmp = new ArrayList<Cake>();
        cakeList.setCakeList(tmp);

        this.cakeService = cakeService;
    }

//    public CakeController() {
//        Cake cake1 = new Cake();
//        cake1.setId(count);
//        count++;
//        cake1.setName("Napoleon");
//        cake1.setCalories(new BigDecimal(250));
//        cake1.setPrice(new BigDecimal(100));
//        cake1.setWeight(new BigDecimal(150));
//        cake1.setImage("napoleon.jpg");
//
//        Cake cake2 = new Cake();
//        cake2.setId(count);
//        count++;
//        cake2.setName("Rose");
//        cake2.setCalories(new BigDecimal(200));
//        cake2.setPrice(new BigDecimal(130));
//        cake2.setWeight(new BigDecimal(100));
//        cake2.setImage("napoleon.jpg");
//
//        List<Cake> tmp = new ArrayList<Cake>();
//        tmp.add(cake1);
//        tmp.add(cake2);
//
//        cakeList.setCakeList(tmp);
//    }

    @GetMapping(value = "get-cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes() {
        return cakeService.getCakes();
    }

    @GetMapping(value = "get-cake-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cake cake(@PathVariable Long id) {
        return cakeList.getCakeList().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CakeNotFoundException("Cake not found"));
    }

    @PostMapping(path = "add_cake", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> addCake(@RequestBody @Valid Cake cake) {
        cake.setId(count);
        count++;
        cakeList.getCakeList().add(cake);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
