package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallBackTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }


    @Test
    void shouldSuccessfulSendValidForm() {
        $("[data-test-id=name] input").setValue("Олег Костромин");
        $("[data-test-id='phone'] input").setValue("+79213068666");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldGetErrorMessageIfNameInvalid() {
        $("[data-test-id=name] input").setValue("Vera Popova");
        $("[data-test-id='phone'] input").setValue("+79110003355");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldGetErrorMessageIfPhoneInvalid() {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Олег Костромин");
        $("[data-test-id='phone'] input").setValue("+7921306866666");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldInvalidCheckbox() {
        $("[data-test-id=name] input").setValue("Олег Костромин");
        $("[data-test-id='phone'] input").setValue("+79213068666");
        $(".button__content").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных" +
                " и разрешаю сделать запрос в бюро кредитных историй"));
    }

    @Test
    void shouldGetErrorMessageIfNameEmpty() {
        $("[data-test-id='phone'] input").setValue("+79213068666");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorMessageIfPhoneEmpty() {
        $("[data-test-id=name] input").setValue("Олег Костромин");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorMessageIfPhoneAndNameEmpty() {
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}


