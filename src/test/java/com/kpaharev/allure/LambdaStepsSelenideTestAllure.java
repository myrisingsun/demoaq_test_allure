package com.kpaharev.allure;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class LambdaStepsSelenideTestAllure {

    String TestAddress = "eroshenkoam/allure-example"; //Эта переменная - тест позитивный
    //String TestAddress = "myrisingsun/demoqa_test"; //Эта переменная - тест негативный (тк на странице нет 76)
    String IssueNumber = "#76"; // номер Issue который мы ищем и проверяем

   @BeforeAll
    static void SetUp (){
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";

   }

    @Test
    public void testGithubIssue () {
        // добавление функционала по отражению в отчете сценария теста
        // SelenideLogger.addListener("allure", new AllureSelenide()); // данная строка добавляет сценарий исполнения в отчет

        step("Открываем страницу - https://github.com/", () -> {
            // документация  по selenide - https://ru.selenide.org/documentation.html
            // открываем страницу https://github.com/
            open("https://github.com/");
        });

        step("Ищем репозиторий " + TestAddress, () -> {
            // находим поле поиска (селектор .header-search-input) и кликаем на него установив фокус на поле
            $(".header-search-input").click();
            // отправляем в поле поиска строку данных eroshenkoam/allure-example (наименование репозитория куда мы переходми)
            $(".header-search-input").sendKeys(TestAddress);
            // нажимаем enter для перехода. .submit - эквивалент нажатия Enter
            $(".header-search-input").submit();
        });

        step("Переходим в репозиторий " + TestAddress, () -> {
            // поиск элемента и клик по нему ( LinkText: возвращает элементы с точным совпадением заданного текста)
            $(linkText(TestAddress)).click();
        });

        step ("Кликаем по таб Issue", () ->{
            // поиск элемента и клие по нему (поиск по частичному содержанию (в данном случае слово Issues), (PartialLinkText возвращает элементы, включающие заданный текст)
            $(partialLinkText("Issues")).click();
        });

        step ("Проверяем наличие на странице Issue c номером " + IssueNumber, () -> {
            // клик по элементу (withText - поиск элемента по тексту (подстроке))
            $(withText(IssueNumber)).click();
        });

    }
}
