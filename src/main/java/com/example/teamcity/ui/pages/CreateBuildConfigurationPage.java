package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreateBuildConfigurationPage extends BasePage {
    private static final String CREATE_BUILD_CONFIG_URL = "/admin/createObjectMenu.html?projectId=%s&showMode=createBuildTypeMenu";

    private final SelenideElement manuallyButton = $("a[href='#createManually']");
    private final SelenideElement buildTypeNameInput = $("#buildTypeName");
    private final SelenideElement buildConfigurationIdInput = $("#buildTypeExternalId");
    private final SelenideElement descriptionInput = $("#description");
    private final SelenideElement createButton = $(".submitButton");

    public static CreateBuildConfigurationPage open(String projectId) {
        return Selenide.open(String.format(CREATE_BUILD_CONFIG_URL, projectId), CreateBuildConfigurationPage.class);
    }

    public void createManuallyBuildConfiguration(String name, String buildConfigurationId, String description) {
        manuallyButton.click();
        buildTypeNameInput.val(name);
        buildConfigurationIdInput.val(buildConfigurationId);
        descriptionInput.val(description);
        createButton.click();
    }
}
