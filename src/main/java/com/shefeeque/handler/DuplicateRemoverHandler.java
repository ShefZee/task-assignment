package com.shefeeque.handler;

public class DuplicateRemoverHandler extends StringHandler {

    @Override
    public String handle(String value) {
        String[] array = value.trim().split(" ");
        StringBuilder newValue = new StringBuilder();
        int length = array.length;
        for(int i = 0; i < length-1; i++){
            if(!array[i].equals(array[i+1])){
                newValue.append(array[i] + " ");
            }
        }
        newValue.append(array[length-1]);

        return handleNext(newValue.toString());
    }
}
