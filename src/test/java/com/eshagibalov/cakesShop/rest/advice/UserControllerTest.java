package com.eshagibalov.cakesShop.rest.advice;

import com.eshagibalov.cakesShop.goods.CakeService;
import com.eshagibalov.cakesShop.rest.controllers.UserController;
import com.eshagibalov.cakesShop.rest.dto.cake.CakeMoreInfo;
import com.eshagibalov.cakesShop.rest.dto.cake.Cakes;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.test.util.AssertionErrors;

import static org.mockito.ArgumentMatchers.any;

public class UserControllerTest {


//    @Test
//    void testCakes() {
//        Cakes cakes = new Cakes();
//        CakeService cakeService = Mockito.mock(CakeService.class);
//        Mockito.doReturn(cakes).when(cakeService).getCakes();
//        UserController userController = new UserController(cakeService);
//        Cakes cakesForTest = userController.cakes();
//        AssertionErrors.assertEquals("", cakesForTest, cakes);
//        Mockito.verify(cakeService, Mockito.times(1)).getCakes();
//    }

//    @Test
//    void testCakeById() {
//        CakeMoreInfo cakeMoreInfo = new CakeMoreInfo();
//        CakeService cakeService = Mockito.mock(CakeService.class);
//        Mockito.doReturn(cakeMoreInfo).when(cakeService).getMoreInfo(any());
//        UserController userController = new UserController(cakeService);
//        CakeMoreInfo cakeForTest = userController.getCakeById(1L);
//        AssertionErrors.assertEquals("", cakeForTest, cakeMoreInfo);
//        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
//        Mockito.verify(cakeService, Mockito.times(1)).getMoreInfo(argumentCaptor.capture());
//        AssertionErrors.assertEquals("", argumentCaptor.getValue(), 1L);
//    }
}
