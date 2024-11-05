package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SteakhouseTest {

    @Test
    @DisplayName("Проверка всего ресторана")
    public void checkSteakhouse() {
        Waitress waitress = new Waitress();
        Kitchen kitchen = new Kitchen();
        PayTerminal payTerminal = new PayTerminal();
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);

        Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, Currency.RUB);
        Dish burger = steakhouse.takeOrder(paycheck);

        Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount(), "Несоответствует стоимость");
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency(), "Несоответствует валюта");
        Assertions.assertEquals(DishType.BURGER, paycheck.getDishType(), "Несоответствует тип еды");
        Assertions.assertEquals(DishType.BURGER, burger.getDishType(), "Не соответствует тип блюда");

    }

}