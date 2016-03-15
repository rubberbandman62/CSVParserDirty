package de.cleancodingcosmos.csvparser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Die letzten beiden Tests schlagen fehl. <code>CSVParserClean</code> ist so zu modifizieren,
 * dass alles Tests fehlerfrei sind.
 */
public class CSVParserDirtyTest {

    private CSVParserDirty csvParser;

    @Before
    public void setUp() {
        csvParser = new CSVParserDirty();
    }


    @Test
    public void testEmptyString() {
        ArrayList<String> actual = csvParser.parseLine("");
        List<String> expected = Arrays.asList("");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testOneDelimeter() {
        ArrayList<String> actual = csvParser.parseLine(";");
        List<String> expected = Arrays.asList("", "");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTwoQuotes() {
        ArrayList<String> actual = csvParser.parseLine("\"\"");
        List<String> expected = Arrays.asList("");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testThreeQuotes() {
        ArrayList<String> actual = csvParser.parseLine("\"\"\"");
        List<String> expected = Arrays.asList("\"");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFourQuotes() {
        ArrayList<String> actual = csvParser.parseLine("\"\"\"\"");
        List<String> expected = Arrays.asList("\"");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testTwoDelimeters() {
        ArrayList<String> actual = csvParser.parseLine(";;");
        List<String> expected = Arrays.asList("", "", "");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testOneElement() {
        ArrayList<String> actual = csvParser.parseLine("Reik");
        List<String> expected = Arrays.asList("Reik");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testTwoElements() {
        ArrayList<String> actual = csvParser.parseLine("Jörg;Reik");
        List<String> expected = Arrays.asList("Jörg", "Reik");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testTwoElementsWithSpace() {
        ArrayList<String> actual = csvParser.parseLine("Jörg; Reik");
        List<String> expected = Arrays.asList("Jörg", " Reik");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testOneElementWithQuotes() {
        ArrayList<String> actual = csvParser.parseLine("\"Reik\"");
        List<String> expected = Arrays.asList("Reik");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testTwoElementsWithQuotes() {
        ArrayList<String> actual = csvParser.parseLine("\"Jörg\";\"Reik\"");
        List<String> expected = Arrays.asList("Jörg", "Reik");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testOneElementWithQuotesAndCommaInside() {
        ArrayList<String> actual = csvParser.parseLine("\"Jörg; Reik\"");
        List<String> expected = Arrays.asList("Jörg; Reik");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testCommaAndElement() {
        ArrayList<String> actual = csvParser.parseLine(";Reik");
        List<String> expected = Arrays.asList("", "Reik");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testElementAndComma() {
        ArrayList<String> actual = csvParser.parseLine("Reik;");
        List<String> expected = Arrays.asList("Reik", "");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testQuoteInsideUnquotedElement() {
        ArrayList<String> actual = csvParser.parseLine("Pe\"ter");
        List<String> expected = Arrays.asList("Pe\"ter");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testQuoteInsideUnQuotedElementAfterSep() {
        ArrayList<String> actual = csvParser.parseLine("Rolf;Pe\"ter");
        List<String> expected = Arrays.asList("Rolf", "Pe\"ter");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testCharsAfterQuotedElement() {
        ArrayList<String> actual = csvParser.parseLine("\"Pe\"ter");
        List<String> expected = Arrays.asList("Peter");
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testCharsAfterQuotedElementWithFollowing() {
        ArrayList<String> actual = csvParser.parseLine("\"Pe\"ter;Rolf");
        List<String> expected = Arrays.asList("Peter", "Rolf");
        Assert.assertEquals(expected, actual);
    }


}