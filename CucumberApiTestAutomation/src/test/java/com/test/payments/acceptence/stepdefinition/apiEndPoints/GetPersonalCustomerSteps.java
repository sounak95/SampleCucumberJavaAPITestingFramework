package com.test.payments.acceptence.stepdefinition.apiEndPoints;


import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import com.test.payments.acceptence.files.resources;
import com.test.payments.acceptence.utils.Objects;
import com.test.payments.acceptence.utils.RestAssuredHelper;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetPersonalCustomerSteps  extends Objects {
    @When("^the User provides customer identifier \"([^\"]*)\" to fetch the customer details$")
    public void the_User_provides_customer_identifier_to_fetch_the_customer_details(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestAssuredHelper.sendGetRequest(resources.getPersonalCustomer(arg1));
    }

    @Then("^Customer first name \"([^\"]*)\" and last name \"([^\"]*)\" is provided to the user$")
    public void customer_first_name_and_last_name_is_provided_to_the_user(String arg1, String arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestAssuredHelper.validateRequestStatus(200);
        assertTrue(js.get("firstName").equals(arg1));
        assertTrue(js.get("lastName").equals(arg2));
    }

    @Then("^System should not return any customer details$")
    public void system_should_not_return_any_customer_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestAssuredHelper.validateRequestStatus(400);
    }
    
    @When("^the User fetches the details using the created CustomerId$")
    public void the_User_fetches_the_details_using_the_created_CustomerId() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        RestAssuredHelper.validateRequestStatus(201);
        String customerId = js.get("customerId");
        RestAssuredHelper.sendGetRequest(resources.getPersonalCustomer(customerId));
        
    }
    
   

}
