package com.kpaharev.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class SelenideTestAllure {

    String TestAddress = "eroshenkoam/allure-example";
    //String TestAddress = "myrisingsun/demoqa_test";

   @BeforeAll
    static void SetUp (){
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";

   }

    @Test
    public void testGithubIssue () {
        // добавление функционала по отражению в отчете сценария теста
        SelenideLogger.addListener("allure", new AllureSelenide());
        // документация  по selenide - https://ru.selenide.org/documentation.html
        // открываем страницу https://github.com/
        open("https://github.com/");
        // находим поле поиска (селектор .header-search-input) и кликаем на него установив фокус на поле
        $(".header-search-input").click();
        // отправляем в поле поиска строку данных eroshenkoam/allure-example (наименование репозитория куда мы переходми)
        $(".header-search-input").sendKeys(TestAddress);
        // нажимаем enter для перехода. .submit - эквивалент нажатия Enter
        $(".header-search-input").submit();
        // поиск элемента и клик по нему ( LinkText: возвращает элементы с точным совпадением заданного текста)
        $(linkText(TestAddress)).click();
        // поиск элемента и клие по нему (поиск по частичному содержанию (в данном случае слово Issues), (PartialLinkText возвращает элементы, включающие заданный текст)
        $(partialLinkText("Issues")).click();
        // клик по элементу (withText - поиск элемента по тексту (подстроке))
        $(withText("#76")).click();
    }
}
