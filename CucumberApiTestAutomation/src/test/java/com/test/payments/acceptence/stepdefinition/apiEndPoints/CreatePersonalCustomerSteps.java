package com.test.payments.acceptence.stepdefinition.apiEndPoints;


import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import com.test.payments.acceptence.files.resources;
import com.test.payments.acceptence.utils.Objects;
//import static com.test.payments.acceptence.utils.Objects.*;
import com.test.payments.acceptence.utils.RestAssuredHelper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreatePersonalCustomerSteps extends Objects{
    @Given("^the User \"([^\"]*)\" is logged in to system$")
    public void the_User_is_logged_in_to_system(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("the User " + arg1 + " is logged in to system$");
    }

    @When("^the User creates a Personal Customer using the details \"([^\"]*)\"$")
    public void the_User_creates_a_Personal_Customer_using_the_details(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestAssuredHelper.sendPostRequest(resources.createPersonalCustomer(), arg1);
    }

    @Then("^A valid CustomerId is provided to the User$")
    public void a_valid_CustomerId_is_provided_to_the_User() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestAssuredHelper.validateRequestStatus(201);
    }

    @Then("^An error message saying \"([^\"]*)\" is provided to the user$")
    public void an_error_message_saying_is_provided_to_the_user(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestAssuredHelper.validateRequestStatus(400);
        assertTrue(js.get("detail").equals(arg1));
    }
}
