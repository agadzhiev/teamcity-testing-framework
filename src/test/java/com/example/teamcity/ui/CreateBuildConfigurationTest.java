package com.example.teamcity.ui;

import com.example.teamcity.api.enums.Endpoint;
import com.example.teamcity.api.models.BuildType;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.requests.CheckedRequests;
import com.example.teamcity.api.spec.Specifications;
import com.example.teamcity.ui.pages.CreateBuildConfigurationPage;
import com.example.teamcity.ui.pages.LoginPage;
import com.example.teamcity.ui.pages.ProjectPage;
import org.testng.annotations.Test;

import static io.qameta.allure.Allure.step;

public class CreateBuildConfigurationTest extends BaseUiTest {

    @Test(description = "User should be able to create build configuration", groups = {"Regression"})
    public void userCreatesProject() {
        // подготовка окружения
        step("Login as user");
        superUserCheckRequests.getRequest(Endpoint.USERS).create(testData.getUser());
        var userCheckedRequest = new CheckedRequests(Specifications.authSpec(testData.getUser()));

        // создать проект через API
        userCheckedRequest.getRequest(Endpoint.PROJECTS).create(testData.getProject());
        userCheckedRequest.<Project>getRequest(Endpoint.PROJECTS).read(testData.getProject().getId());

        // взаимодействие через UI
        LoginPage.open().login(testData.getUser());
        CreateBuildConfigurationPage.open(testData.getProject().getId())
                .createManuallyBuildConfiguration(testData.getBuildType().getName(), testData.getBuildType().getId(), "Описание билд-конфигурации");

        // проверка состояние UI
        ProjectPage projectPage = ProjectPage.openEditPage(testData.getProject().getId());
        boolean isBuildConfigPresent = projectPage.isBuildConfigurationPresent(testData.getBuildType().getName(), "Описание билд-конфигурации");

        softy.assertTrue(isBuildConfigPresent, "Билд-конфигурация не найдена на странице проекта.");

        // проверка состояния API
        var createdProjectBuild = superUserCheckRequests.<BuildType>getRequest(Endpoint.BUILD_TYPES).read("name:" + testData.getBuildType().getName());
        softy.assertNotNull(createdProjectBuild);
    }
}


