package com.shefeeque.handler;


public abstract class StringHandler {

    private StringHandler next;

    public StringHandler setNextHandler(StringHandler next){
        this.next = next;
        return next;
    }

    public abstract String handle(String value);

    protected String handleNext(String value){
        if(next == null){
            return value;
        }
        return next.handle(value);
    }
}
