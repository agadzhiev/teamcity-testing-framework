package com.example.teamcity.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.api.models.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage{
    private static final String LOGIN_URL = "/login.html";

    private final SelenideElement usernameInput = $("#username");
    private final SelenideElement passwordInput = $("#password");
    private final SelenideElement loginButton = $(".btn.loginButton");

    public static LoginPage open() {
        return Selenide.open(LOGIN_URL, LoginPage.class);
    }

    public ProjectsPage login(User user) {
        // Метод val вместо clear, sendKeys
        usernameInput.val(user.getUsername());
        passwordInput.val(user.getPassword());
        loginButton.click();

        return Selenide.page(ProjectsPage.class);
    }
}
