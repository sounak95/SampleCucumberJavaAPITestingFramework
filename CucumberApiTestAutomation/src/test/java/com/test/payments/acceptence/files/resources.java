package com.test.payments.acceptence.files;

public class resources {
    
    public static String createPersonalCustomer()
    {

        String resource="/retail-banking/customers/v1/personal-customers";
        return resource;
    }

    public static String getPersonalCustomer(String strCustomerId)
    {
        String resource="/retail-banking/customers/v1/personal-customers/{strCustomerId}";
        
        return resource.replace("{strCustomerId}", strCustomerId);
    }
}
