package com.test.payments.acceptence.utils;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.cucumber.listener.Reporter;

import com.test.payments.acceptence.stepdefinition.MasterHooks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestAssuredHelper extends Objects {

    public static void sendPostRequest(String endPoint, String payload) throws IOException, InterruptedException
    {

        File file = new File(System.getProperty("user.dir")+"//src//test//java//com//test//payments//acceptence//files//" + payload );


        HashMap<String, String> map = new HashMap<>(); 
        map.put("Content-Type", "application/json");
        map.put("Accept", "application/json");
        map.put("Authorization", "Bearer " + Token);

        res = RestAssured.given()
                .headers(map)
                .body(file)
                .log().all()
                .when()
                .post(endPoint)
                .then().log().all()
                .extract().response();



        Reporter.addStepLog(res.asString());

        int statusCode = res.getStatusCode();
        if (statusCode==401 ||  statusCode==400)
        {
            MasterHooks.getb2btoken();
            map.put("Authorization","Bearer " +Token);
            res = RestAssured.given()
                    .headers(map)
                    .body(file)
                    .log().all()
                    .when()
                    .post(endPoint)
                    .then().log().all()
                    .extract().response();
            Reporter.addStepLog(res.asString());
        }

        js= new JsonPath(res.asString());
    }

    public static void validateRequestStatus(int iStatus)
    {
        res.then()
        .statusCode(iStatus);
    }

    public static String  getBody()
    {
        return res.body().asString().trim();
    }

    public static void sendGetRequest(String endPoint) throws IOException
    {
        HashMap<String, String> map = new HashMap<>(); 

        map.put("Content-Type", "application/json");
        map.put("Authorization", "Bearer " + Token);

        res = RestAssured.given()
                .headers(map)
                .log().all()
                .when()
                .get(endPoint)
                .then().log().ifError()
                .extract().response();

        int statusCode = res.getStatusCode();
        if (statusCode==401)
        {
            MasterHooks.getb2btoken();
            map.put("Authorization","Bearer " +Token);
            res = RestAssured.given()
                    .headers(map)
                    .log().ifValidationFails()
                    .when()
                    .get(endPoint)
                    .then().log().ifError()
                    .extract().response();
            Reporter.addStepLog(res.asString());
        }

        Reporter.addStepLog(res.asString());
        js= new JsonPath(res.asString());
    }


    public static void sendDeleteRequest(String endPoint) throws IOException
    {

        res = RestAssured.given()
                .when()
                .delete(endPoint)
                .then()
                .extract().response();

        Reporter.addStepLog(res.asString());

        int statusCode = res.getStatusCode();

        if (statusCode==401)
        {
            MasterHooks.getb2btoken();

            res = RestAssured.given()
                    .when()
                    .delete(endPoint)
                    .then()
                    .extract().response();

            Reporter.addStepLog(res.asString());
        }

        js= new JsonPath(res.asString());
    }

}
