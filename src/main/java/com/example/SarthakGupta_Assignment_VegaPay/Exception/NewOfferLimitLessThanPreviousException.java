package com.example.SarthakGupta_Assignment_VegaPay.Exception;

import javax.naming.NamingEnumeration;

public class NewOfferLimitLessThanPreviousException extends Exception {
    public NewOfferLimitLessThanPreviousException(String message) {
        super(message);
    }
}
