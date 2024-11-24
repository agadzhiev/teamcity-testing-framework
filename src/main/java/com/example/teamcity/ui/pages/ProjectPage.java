package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class ProjectPage extends BasePage {
    private static final String PROJECT_URL = "/project/%s";

    public SelenideElement title = $("span[class*='ProjectPageHeader']");
    private static final String BUILD_CONFIG_LOCATOR_TEMPLATE = "//td[contains(@class, 'name highlight') and contains(., '%s')]";
    private static final String ADMIN_PROJECT_URL_TEMPLATE = "/admin/editProject.html?projectId=%s";

    @Step("Open project page")
    public static ProjectPage open(String projectId) {
        return Selenide.open(PROJECT_URL.formatted(projectId), ProjectPage.class);
    }

    @Step("Open edit page")
    public static ProjectPage openEditPage(String projectId) {
        return Selenide.open(ADMIN_PROJECT_URL_TEMPLATE.formatted(projectId), ProjectPage.class);
    }

    // todo вынести в отдельный класс?
    public boolean isBuildConfigurationPresent(String buildConfigName, String buildConfigDescription) {
        SelenideElement buildConfigCell = $(By.xpath(String.format(BUILD_CONFIG_LOCATOR_TEMPLATE, buildConfigName)))
                .shouldBe(Condition.visible, Duration.ofSeconds(10));

        boolean nameMatches = buildConfigCell.find("strong").has(Condition.exactText(buildConfigName));
        boolean descriptionMatches = buildConfigCell.find(".smallNote").has(Condition.exactText(buildConfigDescription));

        return nameMatches && descriptionMatches;
    }

}
