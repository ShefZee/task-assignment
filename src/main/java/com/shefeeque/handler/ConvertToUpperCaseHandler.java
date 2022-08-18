package com.shefeeque.handler;


public class ConvertToUpperCaseHandler extends StringHandler {

    @Override
    public String handle(String value) {

        value = value.toUpperCase();
        return handleNext(value);
    }
}
