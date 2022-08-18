package com.shefeeque;

import com.shefeeque.handler.*;
import com.shefeeque.service.impl.CustomFileWriter;
import com.shefeeque.service.impl.CustomStringWriter;
import com.shefeeque.strategy.Writer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class FileWriterTest {

    private static String REMOVE_STUPID = "this is really s*****";
    private static String UPPER_CASE_AND_SHOULD_NOT_REMOVE_STUPID = "THIS IS REALLY STUPID";
    private static String FILE_NAME = "C:\\test\\test.dat";
    private static String STRING_TO_WRITE = "This is really Stupid";
    private static String STRING_WITH_DUPLICATES = "This is is really really Stupid";
    private static String REMOVE_DUPLICATE = "This is really Stupid";

    CustomFileWriter customFileWriter;

    @Before
    public void setUp() throws Exception{
        customFileWriter = CustomFileWriter.getInstance(FILE_NAME);
        customFileWriter.setValue("");
    }

    @After
    public void tearDown() throws Exception {
        customFileWriter = null;
        deleteFile(FILE_NAME);
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
        writer.open(customFileWriter);
        writer.write(STRING_TO_WRITE,customFileWriter,domainHandler);
        writer.close(customFileWriter);

        //then
        assertEquals(REMOVE_STUPID, writer.getValue(customFileWriter));
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
        writer.open(customFileWriter);
        writer.write(STRING_TO_WRITE,customFileWriter,domainHandler);
        writer.close(customFileWriter);

        //then
        assertEquals(UPPER_CASE_AND_SHOULD_NOT_REMOVE_STUPID, writer.getValue(customFileWriter));
    }


    @Test
    public void shouldRemoveDuplicates(){
        //given
        Writer writer = new Writer();

        StringHandler duplicateRemoverHandler = new DuplicateRemoverHandler();
        StringHandler domainHandler = duplicateRemoverHandler;

        //when
        writer.open(customFileWriter);
        writer.write(STRING_WITH_DUPLICATES,customFileWriter,domainHandler);
        writer.close(customFileWriter);

        //then
        assertEquals(REMOVE_DUPLICATE, writer.getValue(customFileWriter));

    }

    @Test
    public void shouldAllowMultipleWrites(){
        //given
        Writer writer = new Writer();

        StringHandler duplicateRemoverHandler = new DuplicateRemoverHandler();
        StringHandler domainHandler = duplicateRemoverHandler;

        //when
        writer.open(customFileWriter);
        writer.write(STRING_WITH_DUPLICATES,customFileWriter,domainHandler);
        writer.close(customFileWriter);
        writer.write(STRING_WITH_DUPLICATES,customFileWriter,domainHandler);

        //then
        assertEquals(REMOVE_DUPLICATE, writer.getValue(customFileWriter));

    }


    private void deleteFile(String fileName){
        File file = new File(fileName);
        file.delete();

    }


}
