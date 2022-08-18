package com.shefeeque;

import com.shefeeque.handler.*;
import com.shefeeque.service.impl.CustomFileWriter;
import com.shefeeque.service.impl.CustomStringWriter;
import com.shefeeque.strategy.Writer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class StringWriterTest {

    private static String REMOVE_STUPID = "this is really s*****";
    private static String UPPER_CASE_AND_SHOULD_NOT_REMOVE_STUPID = "THIS IS REALLY STUPID";
    private static String STRING_TO_WRITE = "This is really Stupid";
    private static String STRING_WITH_DUPLICATES = "This is is really really Stupid";
    private static String REMOVE_DUPLICATE = "This is really Stupid";
    private static String MULTIPLE_WRITES = "THIS IS REALLY STUPID THIS IS REALLY STUPID";

    CustomStringWriter customStringWriter;

    @Before
    public void setUp() throws Exception{
        customStringWriter = CustomStringWriter.getInstance();
        customStringWriter.setValue("");
    }

    @After
    public void tearDown() throws Exception {
        customStringWriter = null;
    }

    @Test
    public void shouldConvertToLowerCaseAndRemoveStupid(){
        //given

        Writer writer = new Writer();

        StringHandler lowerCaseHandler = new ConvertToLowerCaseHandler();
        StringHandler stupidRemoveHandler = new StupidRemoverHandler();
        lowerCaseHandler.setNextHandler(stupidRemoveHandler);
        StringHandler domainHandler = lowerCaseHandler;

        //when
        writer.open(customStringWriter);
        writer.write(STRING_TO_WRITE,customStringWriter,domainHandler);
        writer.close(customStringWriter);

        //then
        assertEquals(REMOVE_STUPID, writer.getValue(customStringWriter));
    }

    @Test
    public void shouldConvertToUpperCaseAndShouldNotRemoveStupid(){
        //given
        Writer writer = new Writer();

        StringHandler upperCaseHandler = new ConvertToUpperCaseHandler();
        StringHandler stupidRemoveHandler = new StupidRemoverHandler();
        upperCaseHandler.setNextHandler(stupidRemoveHandler);
        StringHandler domainHandler = upperCaseHandler;

        //when
        writer.open(customStringWriter);
        writer.write(STRING_TO_WRITE,customStringWriter,domainHandler);
        writer.close(customStringWriter);

        //then
        assertEquals(UPPER_CASE_AND_SHOULD_NOT_REMOVE_STUPID, writer.getValue(customStringWriter));
    }


    @Test
    public void shouldRemoveDuplicates(){
        //given
        Writer writer = new Writer();

        StringHandler duplicateRemoverHandler = new DuplicateRemoverHandler();
        StringHandler domainHandler = duplicateRemoverHandler;

        //when
        writer.open(customStringWriter);
        writer.write(STRING_WITH_DUPLICATES,customStringWriter,domainHandler);
        writer.close(customStringWriter);

        //then
        assertEquals(REMOVE_DUPLICATE, writer.getValue(customStringWriter));

    }

    @Test
    public void shouldIgnoreWritesAfterClose(){
        //given
        Writer writer = new Writer();

        StringHandler upperCaseHandler = new ConvertToUpperCaseHandler();
        StringHandler stupidRemoveHandler = new StupidRemoverHandler();
        upperCaseHandler.setNextHandler(stupidRemoveHandler);
        StringHandler domainHandler = upperCaseHandler;

        //when
        writer.open(customStringWriter);
        writer.write(STRING_TO_WRITE,customStringWriter,domainHandler);
        writer.close(customStringWriter);
        writer.write(STRING_TO_WRITE,customStringWriter,domainHandler);

        //then
        assertEquals(UPPER_CASE_AND_SHOULD_NOT_REMOVE_STUPID, writer.getValue(customStringWriter));
    }

    @Test
    public void shouldAllowMultipleWrites(){
        //given
        Writer writer = new Writer();

        StringHandler upperCaseHandler = new ConvertToUpperCaseHandler();
        StringHandler stupidRemoveHandler = new StupidRemoverHandler();
        upperCaseHandler.setNextHandler(stupidRemoveHandler);
        StringHandler domainHandler = upperCaseHandler;

        //when
        writer.open(customStringWriter);
        writer.write(STRING_TO_WRITE,customStringWriter,domainHandler);
        writer.write(STRING_TO_WRITE,customStringWriter,domainHandler);
        writer.close(customStringWriter);

        //then
        assertEquals(MULTIPLE_WRITES, writer.getValue(customStringWriter));
    }

    @Test
    public void shouldWriteWithoutAnyHandler(){
        //given

        Writer writer = new Writer();

        //when
        writer.open(customStringWriter);
        writer.write(STRING_TO_WRITE,customStringWriter,null);
        writer.close(customStringWriter);

        //then
        assertEquals(STRING_TO_WRITE, writer.getValue(customStringWriter));
    }
}
