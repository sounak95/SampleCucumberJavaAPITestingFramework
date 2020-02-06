Feature: Create a a personal customer
  As a User
  I want to create a Personal Customer

#https://developer.lobdev.fusionfabric.cloud/api/retail-banking-customers-v1-35813e1e-e680-45d1-93b5-763a2cf25620/docs#operation/getPersonalCustomerDetails


  # vocabulary
  # User  : registered User with the bank having access to create a Personal Customer
  # Personal Customer :
  # CustomerId : 
  
   Background:
   	Given the User "ffdcuser1" is logged in to system
   	
  @positive
  Scenario Outline: Create a personal customer using valid details
#   Given the User "ffdcuser1" is logged in to system
    When the User creates a Personal Customer using the details "<CreatePersonalCustomer_payload>"
	Then A valid CustomerId is provided to the User 
  Examples:
	  | CreatePersonalCustomer_payload            |    
	  | CreatePersonalCustomer_valid_payload.json | 
	      
	                                
  @negative
  Scenario Outline: Create a personal customer using in-valid details
#    Given the User "ffdcuser1" is logged in to system
	 When the User creates a Personal Customer using the details "<CreatePersonalCustomer_payload>"
	Then An error message saying "<error_msg>" is provided to the user
	               
  Examples:
	  | CreatePersonalCustomer_payload                   |   error_msg                                     |
	  | CreatePersonalCustomer_in_valid_payload.json     |  Error encounter in parsing the request message |
	  