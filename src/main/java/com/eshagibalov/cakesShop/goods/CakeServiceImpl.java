package com.eshagibalov.cakesShop.goods;

import com.eshagibalov.cakesShop.rest.dao.CakeDao;
import com.eshagibalov.cakesShop.rest.dto.cake.Cake;
import com.eshagibalov.cakesShop.rest.dto.cake.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.cake.Cakes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeServiceImpl implements CakeService {

    private final CakeDao cakeDao;

    @Autowired
    public CakeServiceImpl(CakeDao cakeDao) {
        this.cakeDao = cakeDao;
    }

    @Override
    public Cakes getCakes() {
        List<Cake> cakeEntityList = cakeDao.getAllCakes();
        List<Cake> cakesList = cakeEntityList.stream().map(cakeEntity -> {
            Cake cake = new Cake();
            cake.setId(cakeEntity.getId());
            cake.setName(cakeEntity.getName());
            cake.setCalories(cakeEntity.getCalories());
            cake.setWeight(cakeEntity.getWeight());
            cake.setPrice(cakeEntity.getPrice());
            cake.setImage(cakeEntity.getImage());
            cake.setAvailabilityOfCake(cakeEntity.getAvailabilityOfCake());
            return cake;
        }).filter(cake -> !cake.getAvailabilityOfCake().equals(AvailabilityOfCake.UNAVAILABLE)).collect(Collectors.toList());
        Cakes cakes = new Cakes();
        cakes.setCakeList(cakesList);
        return cakes;
    }

    @Override
    public void addCake(CakeMoreInfo cake) {
        cakeDao.createCake(cake);
    }

    @Override
    public void deleteCake(Long id) {
        cakeDao.deleteCakeById(Math.toIntExact(id));
    }

    @Override
    public CakeMoreInfo getCakeById(Long id){
        Cake cake=cakeDao.getCakeById(id);
        CakeMoreInfo newCake=new CakeMoreInfo();
        newCake.setName(cake.getName());
        newCake.setPrice(cake.getPrice());
        newCake.setCalories(cake.getCalories());
        newCake.setImage(cake.getImage());
        newCake.setWeight(cake.getWeight());
        return newCake;
    }
}
