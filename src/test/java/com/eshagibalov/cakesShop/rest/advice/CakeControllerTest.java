package com.eshagibalov.cakesShop.rest.advice;

import com.eshagibalov.cakesShop.goods.CakeService;
import com.eshagibalov.cakesShop.rest.controllers.CakeController;
import com.eshagibalov.cakesShop.rest.dto.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.Cakes;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import static org.mockito.ArgumentMatchers.any;

public class CakeControllerTest {


    @Test
    void testCakes() {
        Cakes cakes = new Cakes();
        CakeService cakeService = Mockito.mock(CakeService.class);
        Mockito.doReturn(cakes).when(cakeService).getCakes();
        CakeController cakeController = new CakeController(cakeService);
        Cakes cakesForTest = cakeController.cakes();
        AssertionErrors.assertEquals("", cakesForTest, cakes);
        Mockito.verify(cakeService, Mockito.times(1)).getCakes();
    }

    @Test
    void testCakeById() {
        CakeMoreInfo cakeMoreInfo = new CakeMoreInfo();
        CakeService cakeService = Mockito.mock(CakeService.class);
        Mockito.doReturn(cakeMoreInfo).when(cakeService).getMoreInfo(any());
        CakeController cakeController = new CakeController(cakeService);
        CakeMoreInfo cakeForTest = cakeController.getCakeById(1L);
        AssertionErrors.assertEquals("", cakeForTest, cakeMoreInfo);
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.verify(cakeService, Mockito.times(1)).getMoreInfo(argumentCaptor.capture());
        AssertionErrors.assertEquals("", argumentCaptor.getValue(), 1L);
    }
}
