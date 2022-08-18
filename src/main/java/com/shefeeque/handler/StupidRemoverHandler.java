package com.shefeeque.handler;

public class StupidRemoverHandler extends StringHandler {

    @Override
    public String handle(String value) {

        value = value.replace("stupid","s*****");
        return handleNext(value);
    }
}
