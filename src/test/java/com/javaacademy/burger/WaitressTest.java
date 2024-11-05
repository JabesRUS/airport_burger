package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class WaitressTest {
    Kitchen kitchen;
    Waitress waitress;
    DishType dishType;

    @BeforeEach
    public void setUp() {
        kitchen = Mockito.mock(Kitchen.class);
        waitress = new Waitress();
    }

    @Test
    @DisplayName("Проверка Официанта №1")
    public void checkOfficiant1() {
        boolean result = waitress.giveOrderToKitchen(DishType.BURGER, kitchen);

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Проверка Официанта №1")
    public void checkOfficiant2() {
        boolean result = waitress.giveOrderToKitchen(DishType.FUAGRA, kitchen);

        Assertions.assertFalse(result);
    }

}