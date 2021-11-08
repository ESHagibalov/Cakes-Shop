package com.eshagibalov.cakesShop.goods;

import com.eshagibalov.cakesShop.exceptions.CakeNotFoundException;
import com.eshagibalov.cakesShop.rest.dto.Cake;
import com.eshagibalov.cakesShop.rest.dto.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.Cakes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeServiceImpl implements CakeService {

    private final CakeRepository cakeRepository;

    @Autowired
    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public Cakes getCakes() {
        List<CakeEntity> cakeEntityList = cakeRepository.findAll();
        List<Cake> cakesList = cakeEntityList.stream().map(cakeEntity -> {
            Cake cake = new Cake();
            cake.setId(cakeEntity.getId());
            cake.setName(cakeEntity.getName());
            cake.setCalories(cakeEntity.getCalories());
            cake.setWeight(cakeEntity.getWeight());
            cake.setPrice(cakeEntity.getPrice());
            cake.setImage(cakeEntity.getImage());
            return cake;
        }).collect(Collectors.toList());
        Cakes cakes = new Cakes();
        cakes.setCakeList(cakesList);

        return cakes;
    }

    @Override
    public CakeMoreInfo getMoreInfo(Long id) {
        return cakeRepository.findById(id)
                .map(cakeEntity -> {
                    CakeMoreInfo cakeMoreInfo = new CakeMoreInfo();
                    cakeMoreInfo.setId(cakeEntity.getId());
                    cakeMoreInfo.setName(cakeEntity.getName());
                    cakeMoreInfo.setCalories(cakeEntity.getCalories());
                    cakeMoreInfo.setWeight(cakeEntity.getWeight());
                    cakeMoreInfo.setPrice(cakeEntity.getPrice());
                    cakeMoreInfo.setImage(cakeEntity.getImage());
                    cakeMoreInfo.setComposition(cakeEntity.getCompositions());
                    cakeMoreInfo.setShelfLife(cakeEntity.getShelfLife());
                    return cakeMoreInfo;
                }).orElseThrow(()-> new CakeNotFoundException("Wrong cake id"));
    }
}
