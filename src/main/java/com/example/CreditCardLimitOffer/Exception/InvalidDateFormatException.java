package com.example.CreditCardLimitOffer.Exception;

public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super("Date should be in the format : yyyy-mm-ddTHH:mm:ss");
    }
}
