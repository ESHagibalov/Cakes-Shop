package com.eshagibalov.cakesShop.rest.controllers;

import com.eshagibalov.cakesShop.rest.dao.CakeDao;
import com.eshagibalov.cakesShop.rest.dto.cake.Cake;
import com.eshagibalov.cakesShop.rest.dto.cake.CakeMoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jbdc")
public class JDBController {
    private final CakeDao cakeDao;

    @Autowired
    public JDBController(CakeDao cakeDao) {
        this.cakeDao = cakeDao;
    }

    @PostMapping()
    public void create(@RequestBody CakeMoreInfo cake){
        cakeDao.createCake(cake);
    }

    @GetMapping("/{id}")
    public Cake get(@PathVariable int id){
        return cakeDao.getCakeById((long) id);
    }

    @GetMapping()
    public List<Cake> getAll(){
        return cakeDao.getAllCakes();
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody CakeMoreInfo cake){
        cakeDao.updateCake(id, cake);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        cakeDao.deleteCakeById(id);
    }
}
