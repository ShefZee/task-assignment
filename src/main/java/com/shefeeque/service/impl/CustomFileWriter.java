package com.shefeeque.service.impl;

import com.shefeeque.service.WriterStrategy;
import lombok.Data;


import java.io.*;


@Data
public class CustomFileWriter implements WriterStrategy {

    private boolean active;
    private String value;
    private String fileName;

    private static volatile CustomFileWriter instance;


    @Override
    public void open() {
        this.setActive(true);
    }

    @Override
    public void close() {
        this.setActive(false);
    }

    @Override
    public String getValue()  {
        BufferedReader br;
        StringBuilder value = new StringBuilder();


        try {
            br = new BufferedReader(new FileReader(getFileName()));
            String st;

            while ((st = br.readLine()) != null)
                value.append(st);

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return value.toString().trim();

    }

    @Override
    public void write(String newValue) {
        if(this.isActive()){
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
                writer.append(' ');
                writer.append(newValue);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private CustomFileWriter(){

    }

    synchronized public static CustomFileWriter getInstance(String fileName){
        if(instance == null){
            synchronized(CustomFileWriter.class){
                if(instance == null){
                    instance = new CustomFileWriter();
                    instance.setFileName(fileName);

                }
            }
        }
        return instance;
    }



}
