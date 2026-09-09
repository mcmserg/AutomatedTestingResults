package ru.netology.TestingResuils.test;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.TestingResuils.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.*;


public class TestResults {

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("https://netology.ru/");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");

//кликаем на вкладку "Программирование"
        $("a[href ='/development']").click();

// переход на другую страницу, в поле поиск вводим "Инженер по тестированию" ждем 10 сек нажимаем ввод
        $("input[placeholder='Поиск по каталогу']")
                .shouldBe(visible, Duration.ofSeconds(10))
                .setValue("Инженер по тестированию")
                .press(Keys.ENTER);

// кликаем на курс "Инженер по тестированию"
        $("[data-name='Инженер по тестированию']")
                .shouldBe(visible, Duration.ofSeconds(12))
                .click();
        $("h1")
                .should(Condition.text("Инженер по тестированию"), Duration.ofSeconds(12))
                .should(visible);
// переход на нижнюю форму заполнения
        SelenideElement form = $$("form").last();
// заполняем поля формы, кликаем по checkbox, клик "Записаться"
        form.$("[name='first_name']").setValue(validUser.getName());
        form.$("[name='phone']").setValue(validUser.getPhone());
        form.$("[name='email']").setValue(validUser.getEmail());
        form.$("input[name='checkbox_personal_data_agreement']").parent().click();
        form.$("input[name='checkbox_subscribe']").parent().click();
// клик на форму "записаться" закомментировано т.к. запрещено
        //    form. $("button[name='buttons.orderButton']").click();
    }
}
