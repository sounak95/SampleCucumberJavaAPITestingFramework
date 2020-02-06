Feature: End to End scenario
	As a User  
	I want to create a Personal Customer
	and validate it's details
	
  # vocabulary
  # User  : registered User with the bank having access to create a Personal Customer
  # Personal Customer :
  # CustomerId :
  
	
  Background:
   	Given the User "ffdcuser1" is logged in to system
	
   @positive
  Scenario Outline: Create a personal customer using valid details and validate it's details
    When the User creates a Personal Customer using the details "<CreatePersonalCustomer_payload>"
    And the User fetches the details using the created CustomerId
    Then Customer first name "<firstName>" and last name "<lastName>" is provided to the user
	
  Examples:
	  | CreatePersonalCustomer_payload            |   firstName   |   lastName   |  
	  | CreatePersonalCustomer_valid_payload.json |  Juan        |   Dela Cruz  |