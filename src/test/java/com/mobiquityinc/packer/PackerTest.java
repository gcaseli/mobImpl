package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

public class PackerTest {

    private static final String FILE_PATH = "src/test/java/resources/";
    private static final String TEST_FILE_TXT = "testfile.txt";
    private static final String EMPTY_FILE_TXT = "emptyfile.txt";

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionEmptyPath() throws APIException {
        Packer.pack("");
    }

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionFileNotExists() throws APIException {
        Packer.pack(TEST_FILE_TXT);
    }

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionFileIsEmpty() throws APIException {
        Packer.pack(FILE_PATH + EMPTY_FILE_TXT);
    }

    @Test()
    public void shouldReturnValuesCorrect() throws APIException {

        long startTime = System.currentTimeMillis();

        String pack = Packer.pack(FILE_PATH + TEST_FILE_TXT);

        long endTime = System.currentTimeMillis();

        System.out.println("That took " + (endTime - startTime) + " milliseconds");

        String resultExpected = "4\n" +
                "-\n" +
                "2,7\n" +
                "8,9";

        Assert.assertNotNull(pack);

        Assert.assertEquals(resultExpected, pack);
    }


}