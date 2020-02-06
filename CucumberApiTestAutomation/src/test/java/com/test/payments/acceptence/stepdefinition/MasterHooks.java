package com.test.payments.acceptence.stepdefinition;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.payments.acceptence.utils.*;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class MasterHooks extends Objects {
    //	@Before

    public static void getb2btoken() throws IOException {
        prop=new Properties();
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//test//java//com//test//payments//acceptence//files//env.properties");
        prop.load(fis);

        /*
         * Step1: Using the “grant_type”, “client_id”, “client_secret”, “scope” as form parameter send a post request to the ffdc token server.
         */
        
        RestAssured.baseURI= prop.getProperty("HOST") + "/login/v1/equation-dev";

        Response    res =   given()
                .config(RestAssured.config()
                        .encoderConfig(EncoderConfig.encoderConfig()
                                .encodeContentTypeAs("x-www-form-urlencoded",
                                        ContentType.URLENC)))
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("grant_type", "client_credentials")  
                .formParam("client_id", prop.getProperty("client_id"))
                .formParam("client_secret", prop.getProperty("client_secret"))
                .formParam("scope", "openid")
                .when()
                .post("/oidc/token")
                .then().log().all()
                .statusCode(200)
                .extract().response();

        /*
         * Step2: As a result a token will be returned from the token server. 
         * 
         */

        JsonPath js= new JsonPath(res.asString());

        Token = js.get("access_token"); 
        System.out.println("Token: " +Token);

        System.out.println("Before hook executed");
        RestAssured.baseURI=prop.getProperty("HOST");
    }

}
