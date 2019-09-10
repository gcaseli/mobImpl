package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

public class PackerTest {

    public static final String FILE_PATH = "src/test/java/resources/";
    public static final String TEST_FILE_TXT = "testfile.txt";
    public static final String EMPTY_FILE_TXT = "emptyfile.txt";

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
        String pack = Packer.pack(FILE_PATH + TEST_FILE_TXT);

        String resultExpected = "4\n" +
                "-\n" +
                "2,7\n" +
                "8,9";

        Assert.assertNotNull(pack);

        Assert.assertEquals(resultExpected, pack);
    }


}