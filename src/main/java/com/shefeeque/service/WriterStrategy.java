package com.shefeeque.service;

public interface WriterStrategy {

    void open();
    void close();
    String getValue();

    void write(String value);
}
