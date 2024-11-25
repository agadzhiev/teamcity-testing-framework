package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CreateBuildConfigurationPage extends BasePage {
    private static final String CREATE_BUILD_CONFIG_URL = "/admin/createObjectMenu.html?projectId=%s&showMode=createBuildTypeMenu#createManually";

    private final SelenideElement manuallyButton = $(".createOption.readyToUseOption.expanded");
    private final SelenideElement buildTypeNameInput = $("#buildTypeName");
    private final SelenideElement buildConfigurationIdInput = $("#buildTypeExternalId");
    private final SelenideElement descriptionInput = $("#description");
    private final SelenideElement createButton = $(".submitButton");

    public static CreateBuildConfigurationPage open(String projectId) {
        return Selenide.open(String.format(CREATE_BUILD_CONFIG_URL, projectId), CreateBuildConfigurationPage.class);
    }

    @Step("Create manually build configuration")
    public void createManuallyBuildConfiguration(String name, String buildConfigurationId, String description) {
        manuallyButton.shouldBe(Condition.visible, BASE_WAITING).click();
        buildTypeNameInput.val(name);
        buildConfigurationIdInput.val(buildConfigurationId);
        descriptionInput.val(description);
        createButton.click();
    }
}
