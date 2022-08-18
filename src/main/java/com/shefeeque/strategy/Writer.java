package com.shefeeque.strategy;

import com.shefeeque.handler.StringHandler;
import com.shefeeque.service.WriterStrategy;

public class Writer {

    public void write(String stringToWrite, WriterStrategy writerStrategy, StringHandler handler){

        if(null!=handler){
            stringToWrite =  handler.handle(stringToWrite.trim());
        }
        writerStrategy.write(stringToWrite);

    }

    public String getValue(WriterStrategy writerStrategy){
        return writerStrategy.getValue();
    }

    public void open(WriterStrategy writerStrategy){
        writerStrategy.open();
    }

    public void close(WriterStrategy writerStrategy){
        writerStrategy.close();
    }
}
