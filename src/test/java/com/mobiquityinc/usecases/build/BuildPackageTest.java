package com.mobiquityinc.usecases.build;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class BuildPackageTest {

    private static final String FILE_PATH = "src/test/java/resources/";
    private static final String TEST_FILE_TXT = "testfile.txt";
    private static final String PACKAGE_WEIGHT_WRONG = "packageWeightMoreThan100.txt";
    private static final String PACKAGE_ITEMS_WRONG = "packageItemsMoreThan15.txt";
    private static final String ITEM_WEIGHT_WRONG = "itemWeightMoreThan100.txt";
    private static final String ITEM_COST_WRONG = "itemCostMoreThan100.txt";

    @Test
    public void shouldNotBeEmpty() throws APIException {

        BuildPackage buildPackage = new BuildPackageImpl();

        List<PackageVO> packageVOS = buildPackage.getPackagesFromFile(FILE_PATH + TEST_FILE_TXT);

        Assert.assertEquals(packageVOS.size(), 4);
    }

    @Test
    public void shouldHasPackageValues() throws APIException {

        BuildPackage buildPackage = new BuildPackageImpl();

        List<PackageVO> packageVOS = buildPackage.getPackagesFromFile(FILE_PATH + TEST_FILE_TXT);

        PackageVO packageVO = packageVOS.get(0);

        Assert.assertNotNull(packageVO);

        Assert.assertEquals(packageVO.getPackageWeight(), Double.valueOf(81));

        Assert.assertEquals(packageVO.getItems().size(), 6);
    }

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionPackageWeightWrong() throws APIException {
        packagesFromFile(PACKAGE_WEIGHT_WRONG);
        return;
    }

    private void packagesFromFile(String packageWeightWrong) throws APIException {
        BuildPackage buildPackage = new BuildPackageImpl();

        buildPackage.getPackagesFromFile(FILE_PATH + packageWeightWrong);
    }

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionPackageItemsWrong() throws APIException {
        packagesFromFile(PACKAGE_ITEMS_WRONG);
    }

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionItemWeightWrong() throws APIException {
        packagesFromFile(ITEM_WEIGHT_WRONG);
    }

    @Test(expected = APIException.class)
    public void shouldThrownAnExceptionItemCostWrong() throws APIException {
        packagesFromFile(ITEM_COST_WRONG);
    }
}
