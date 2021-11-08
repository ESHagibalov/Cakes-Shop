package com.eshagibalov.cakesShop.rest.controllers;

import com.eshagibalov.cakesShop.goods.CakeService;
import com.eshagibalov.cakesShop.rest.dto.Cake;
import com.eshagibalov.cakesShop.rest.dto.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.Cakes;
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
//@RequestMapping ("v1/cakes")
public class CakeController {
    private final Cakes cakeList = new Cakes();
    private static long idCounter = 0;
    private final CakeService cakeService;

    @Autowired
    public CakeController(CakeService cakeService) {
        List<Cake> tmp = new ArrayList<Cake>();
        cakeList.setCakeList(tmp);
        this.cakeService = cakeService;
    }


//   public CakeController() {
//        Cake cake1 = new Cake();
//        cake1.setId(idCounter);
//        idCounter++;
//        cake1.setName("Napoleon");
//        cake1.setPrice(new BigDecimal(100));
//        cake1.setWeight(new BigDecimal(100));
//        cake1.setImage("napoleon.jpg");
//        cake1.setCalories(new BigDecimal(100));
//        Cake cake2 = new Cake();
//        cake2.setId(2L);
//        idCounter++;
//        cake2.setName("Rose");
//        cake2.setPrice(new BigDecimal(200));
//        cake2.setWeight(new BigDecimal(200));
//        cake2.setImage("napoleon.jpg");
//        cake2.setCalories(new BigDecimal(200));
//        List<Cake> tmp = new ArrayList<Cake>();
//        tmp.add(cake1);
//        tmp.add(cake2);
//        cakeList.setCakeList(tmp);
//    }

    @GetMapping(value = "cakes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cakes cakes() {
        return cakeService.getCakes();
    }

    @GetMapping(value = "cake/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CakeMoreInfo getCakeById(@PathVariable Long id) {
        return cakeService.getMoreInfo(id);
    }

    @PostMapping(path = "cakes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cake> createCake(@RequestBody @Valid Cake newCake) {
        newCake.setId(idCounter);
        idCounter++;
        cakeList.getCakeList().add(newCake);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}