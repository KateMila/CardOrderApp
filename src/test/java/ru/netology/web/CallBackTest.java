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
    void shouldTestCorrectForm() {
        $("[data-test-id=name] input").setValue("Костромин Олег");
        $("[data-test-id='phone'] input").setValue("+79213068666");
        $("[data-test-id='agreement']").click();
        $(".button__content").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}

