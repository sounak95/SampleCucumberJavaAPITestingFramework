@tag1
Feature: Retrieve a Personal Customer
  As a User
  I want to retrieve the details of a Personal Customer
  
  # vocabulary
  # User  : registered User with the bank having access to create a Personal Customer
  # Personal Customer : 
  # CustomerId : 
   
   Background:
   	Given the User "ffdcuser1" is logged in to system
 
 @smoke @positive
  Scenario Outline: retrieve details of an existing Personal Customer
    When the User provides customer identifier "<customerId>" to fetch the customer details
	Then Customer first name "<firstName>" and last name "<lastName>" is provided to the user
   Examples:	
	  | customerId        | firstName   |   lastName   |
	  | 300068            | Juan        |   Dela Cruz  |

	
 @Negative
  Scenario Outline: retrieve details of a non-existing Personal Customer
    When the User provides customer identifier "<customerId>" to fetch the customer details
    Then System should not return any customer details 
 	Examples:
 	| customerId        | 
	| 111               | 
 	   