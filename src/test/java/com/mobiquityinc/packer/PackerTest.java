package com.mobiquityinc.packer;

import static org.junit.Assert.*;

import com.mobiquityinc.exception.APIException;
import org.junit.Test;

public class PackerTest {

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionEmptyPath() throws APIException {
        Packer.pack("");
    }

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionFileNotExists() throws APIException {
        Packer.pack("/testfile.txt");
    }
}