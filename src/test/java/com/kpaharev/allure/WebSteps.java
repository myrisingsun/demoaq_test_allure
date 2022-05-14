package com.kpaharev.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {

    @Step("Открываем страницу - https://github.com/")
    public void openMainPage () {
        // документация  по selenide - https://ru.selenide.org/documentation.html
        // открываем страницу https://github.com/
        open("https://github.com/");
    }

    @Step ("Ищем репозиторий {repo}")
    public void searchForRepository (String repo) {
        // находим поле поиска (селектор .header-search-input) и кликаем на него установив фокус на поле
        $(".header-search-input").click();
        // отправляем в поле поиска строку данных eroshenkoam/allure-example (наименование репозитория куда мы переходми)
        $(".header-search-input").sendKeys(repo);
        // нажимаем enter для перехода. .submit - эквивалент нажатия Enter
        $(".header-search-input").submit();
    }

    @Step ("Переходим в репозиторий {repo}")
    public void clickOnRepositoryLink (String repo) {
        // поиск элемента и клик по нему ( LinkText: возвращает элементы с точным совпадением заданного текста)
        $(linkText(repo)).click();
    }

    @Step ("Кликаем по таб Issue")
    public void openIssuesTab () {
        $(partialLinkText("Issues")).click();
    }

    @Step ("Проверяем наличие на странице Issue c номером {number}")
    public void shouldSeeIssueWithNumber (int number){
        // клик по элементу (withText - поиск элемента по тексту (подстроке))
        $(withText("#" + number)).should(Condition.visible);
    }
}
