package com.example.teamcity.api.api;

import com.example.teamcity.api.models.User;
import com.example.teamcity.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class BuildConfigurationTest extends BaeApiTest{
    @Test
    public void buildconfigurationTest(){
        var user = User.builder()
                .username("asad_admin")
                .password("Otvet300")
                .build();

        var token = RestAssured
                .given()
                .spec(Specifications.getSpec().authSpec(user))
                .get("/authenticationTest.html?crsf")
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .extract().asString();
        System.out.println(token);
    }
}
