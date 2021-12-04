package com.eshagibalov.cakesShop.goods;

import com.eshagibalov.cakesShop.rest.dto.cake.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.cake.Cakes;

public interface CakeService {

    Cakes getCakes();

    CakeMoreInfo getMoreInfo(Long id);

    void addCake(CakeMoreInfo cake);

}
