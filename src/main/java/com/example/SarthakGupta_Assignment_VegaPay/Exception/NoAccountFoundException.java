package com.example.SarthakGupta_Assignment_VegaPay.Exception;

import ch.qos.logback.core.net.ssl.SSL;

public class NoAccountFoundException extends Exception {
    public NoAccountFoundException(String message) {
        super(message);
    }
}
