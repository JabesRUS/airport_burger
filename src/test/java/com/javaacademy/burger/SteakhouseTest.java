package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SteakhouseTest {
    Waitress waitress;
    Kitchen kitchen;
    PayTerminal payTerminal;

    @BeforeEach
    public void setUp() {
        waitress = new Waitress();
        kitchen = new Kitchen();
        payTerminal = new PayTerminal();
    }

    @Test
    @DisplayName("Проверка всего ресторана")
    public void checkSteakhouse() {
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);

        Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, Currency.RUB);
        Dish burger = steakhouse.takeOrder(paycheck);

        Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount(), "Несоответствует стоимость");
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency(), "Несоответствует валюта в чеке");
        Assertions.assertEquals(DishType.BURGER, paycheck.getDishType(), "Несоответствует тип блюда в чеке");
        Assertions.assertEquals(DishType.BURGER, burger.getDishType(), "Не соответствует тип блюда в заказе");

    }

    @Test
    @DisplayName("Проверка ресторана санэпидемстанцией")
    public void checkSteakhouseSes() {
        PayTerminal payTerminalMock = Mockito.mock(PayTerminal.class);
        Paycheck fakePaycheck = new Paycheck(BigDecimal.valueOf(700), Currency.RUB, DishType.RIBS);
        Steakhouse steakhouseSpy = Mockito.spy(new Steakhouse(waitress, kitchen, payTerminalMock));

        Mockito.when(steakhouseSpy.makeOrder(DishType.RIBS, Currency.RUB)).thenReturn(fakePaycheck);
        Paycheck paycheck = steakhouseSpy.makeOrder(DishType.RIBS, Currency.RUB);
        Dish ribs = steakhouseSpy.takeOrder(paycheck);

        Assertions.assertEquals(BigDecimal.valueOf(700), paycheck.getTotalAmount(), "Несоответствует стоимость");
        Assertions.assertEquals(Currency.RUB, paycheck.getCurrency(), "Несоответствует валюта в чеке");
        Assertions.assertEquals(DishType.RIBS, paycheck.getDishType(), "Несоответствует тип блюда в чеке");
        Assertions.assertEquals(DishType.RIBS, ribs.getDishType(), "Не соответствует тип блюда в заказе");

    }

    @Test
    @DisplayName("Проверка ресторана из нагологой")
    public void checkSteakhouseTaxService() {
        Waitress waitressMock = Mockito.mock(Waitress.class);
        Kitchen kitchenMock =Mockito.mock(Kitchen.class);
        PayTerminal payTerminalSpy = Mockito.spy(new PayTerminal());
        Steakhouse steakhouseSpy = Mockito.spy(new Steakhouse(waitressMock, kitchenMock, payTerminalSpy));
        Paycheck paycheckFake = new Paycheck(BigDecimal.valueOf(700), Currency.RUB, DishType.RIBS);

        Mockito.doReturn(paycheckFake).when(steakhouseSpy.makeOrder(DishType.RIBS, Currency.RUB));
        Mockito.when(steakhouseSpy.makeOrder(DishType.RIBS, Currency.RUB)).thenReturn(paycheckFake);

        Paycheck paycheck1 = steakhouseSpy.makeOrder(DishType.RIBS, Currency.RUB);

        Assertions.assertEquals();

    }


}