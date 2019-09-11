package com.mobiquityinc.usecases;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.usecases.read.ReaderPackage;
import com.mobiquityinc.usecases.read.ReaderPackageImpl;
import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ReaderPackageTest {

    @Test
    public void shouldNotBeEmpty() throws APIException {

        ReaderPackage readerPackage = new ReaderPackageImpl();

        List<PackageVO> packageVOS = readerPackage.getPackagesFromFile("src/test/java/resources/testfile.txt");

        Assert.assertEquals(packageVOS.size(), 4);
    }

    @Test
    public void shouldHasPackageValues() throws APIException {

        ReaderPackage readerPackage = new ReaderPackageImpl();

        List<PackageVO> packageVOS = readerPackage.getPackagesFromFile("src/test/java/resources/testfile.txt");

        PackageVO packageVO = packageVOS.get(0);

        Assert.assertNotNull(packageVO);

        Assert.assertEquals(packageVO.getPackageWeight(), Double.valueOf(81));

        Assert.assertEquals(packageVO.getItems().size(), 6);
    }

}
