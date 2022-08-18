package com.shefeeque.handler;


public class ConvertToLowerCaseHandler extends StringHandler {

    @Override
    public String handle(String value) {

        value = value.toLowerCase();
        return handleNext(value);
    }
}
