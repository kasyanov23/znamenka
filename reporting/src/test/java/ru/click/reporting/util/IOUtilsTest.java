package ru.click.reporting.util;

import lombok.val;
import org.junit.Test;

import java.io.UncheckedIOException;

import static org.junit.Assert.assertEquals;

public class IOUtilsTest {

    @Test
    public void fileToString() throws Exception {
        val expectedString  = "SELECT * FROM tableA;";
        val actualString = IOUtils.fileToString("test-file.sql");
        assertEquals(expectedString, actualString);
    }

    @Test(expected = UncheckedIOException.class)
    public void fileNotFound() throws Exception {
        IOUtils.fileToString("not-found-file.sql");
    }
}