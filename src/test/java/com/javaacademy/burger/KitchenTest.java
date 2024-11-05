package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class KitchenTest {
    private Dish burgerTest;
    private Dish fuagra;
    private Map<DishType, Queue<Dish>> completedDishes; //стол готовой еды
    Kitchen kitchen;
    Kitchen kitchenBurger;

    @BeforeEach
    public void setUp() {
        kitchen = new Kitchen();
        kitchenBurger = new Kitchen();
    }

    @Test
    @DisplayName("Проверка кухни")
    public void checkKitchen() {
        kitchen.cook(DishType.BURGER);

        Assertions.assertEquals(new Dish(DishType.BURGER),
                kitchen.getCompletedDishes().get(DishType.BURGER).peek(), "Ожидался бургер");
    }

    @Test
    @DisplayName("Проверка газа на кухне")
    public void checkGas() {
        kitchen.setHasGas(false);

        Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(DishType.BURGER));
    }

    @Test
    @DisplayName("Проверка газа на кухне")
    public void checkDishFuagra() {

        Assertions.assertThrows(UnsupportedDishTypeException.class, () -> kitchen.cook(DishType.FUAGRA));
    }

}