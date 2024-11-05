package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PayTerminalTest {
//    Paycheck pay(DishType dishType, Currency currency)
    PayTerminal payTerminal;
    Dish burger;

    @BeforeEach
    public void setUp() {
        payTerminal = new PayTerminal();
        burger = new Dish(DishType.BURGER);
    }

    @Test
    @DisplayName("Проверка оплаты")
    public void checkTerminal() {
        Paycheck paycheck = payTerminal.pay(DishType.BURGER, Currency.RUB);

        Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount(), "Несоответствует стоимость");
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency(), "Несоответствует валюта");
        Assertions.assertEquals(burger.getDishType(), paycheck.getDishType(), "Несоответствует тип еды");
    }

    @Test
    @DisplayName("Проверка валюты на ексепшен")
    public void checkCurrency() {

        Assertions.assertThrows(NotAcceptedCurrencyException.class,
                () -> payTerminal.pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS));
    }
}