package com.aui.automation.api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class BaseFlow {
    public String resource;
    public String firstNamePath;
    public String lastNamePath;
    public  String response;

    @BeforeClass
    public void before() {
        RestAssured.baseURI = "https://reqres.in/api/";
        resource="/users/";
        firstNamePath= "data.first_name";
        lastNamePath="data.last_name";

    }
    public String sendRequest(String userId ,int codeStatus){
        return response= given()
                .pathParam("id",userId)
                .log().all()
                .when().get(resource+"{id}")
                .then().log().all().assertThat().statusCode(codeStatus)
                .extract().response().asString();
    }

}
