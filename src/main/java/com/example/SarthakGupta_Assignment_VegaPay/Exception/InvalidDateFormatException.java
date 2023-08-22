package com.example.SarthakGupta_Assignment_VegaPay.Exception;

import jakarta.persistence.criteria.CriteriaBuilder;

public class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException() {
        super("Date should be in the format : yyyy-mm-ddTHH:mm:ss");
    }
}
