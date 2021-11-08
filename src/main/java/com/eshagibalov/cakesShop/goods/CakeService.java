package com.eshagibalov.cakesShop.goods;

import com.eshagibalov.cakesShop.rest.dto.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.Cakes;

public interface CakeService {

    Cakes getCakes();

    CakeMoreInfo getMoreInfo(Long id);
}
