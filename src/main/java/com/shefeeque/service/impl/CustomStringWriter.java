package com.shefeeque.service.impl;

import com.shefeeque.service.WriterStrategy;
import lombok.Data;

@Data
public class CustomStringWriter implements WriterStrategy {

    private boolean active;
    private String value;

    private static volatile CustomStringWriter instance;

    private CustomStringWriter(){

    }


    @Override
    public void open() {
        this.setActive(true);
    }

    @Override
    public void close() {
        this.setActive(false);
    }

    @Override
    public void write(String value) {
        if(this.isActive()){
            String newValue;
            if(!"".equals(this.getValue())){
                newValue = this.getValue() + " " + value;
            }else{
                newValue = value;
            }

            this.setValue(newValue);
        }

    }

    synchronized public static CustomStringWriter getInstance(){
        if(instance == null){
            synchronized(CustomStringWriter.class){
                if(instance == null){
                    instance = new CustomStringWriter();

                }
            }
        }
        return instance;
    }

}
