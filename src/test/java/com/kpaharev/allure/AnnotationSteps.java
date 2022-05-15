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

public class AnnotationSteps {

    private static final String TestAddress = "eroshenkoam/allure-example"; //Эта переменная - тест позитивный
    //String TestAddress = "myrisingsun/demoqa_test"; //Эта переменная - тест негативный (тк на странице нет 76)
    private static final Integer IssueNumber = 76; // номер Issue который мы ищем и проверяем

   @BeforeAll
    static void setUp (){
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";

   }

    @Test
    public void testGithubIssue () {
        // добавление функционала по отражению в отчете сценария теста
        SelenideLogger.addListener("allure", new AllureSelenide()); // данная строка добавляет сценарий исполнения в отчет

        WebSteps steps = new WebSteps ();
        steps.openMainPage();
        steps.searchForRepository(TestAddress);
        steps.clickOnRepositoryLink(TestAddress);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(IssueNumber);


    }
}
