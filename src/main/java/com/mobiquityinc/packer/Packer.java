package com.mobiquityinc.packer;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.usecases.processor.ProcessorPackage;
import com.mobiquityinc.usecases.processor.ProcessorPackageImpl;
import com.mobiquityinc.usecases.build.BuildPackage;
import com.mobiquityinc.usecases.build.BuildPackageImpl;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.List;

public class Packer {

    private static BuildPackage buildPackage = new BuildPackageImpl();

    private static ProcessorPackage processorPackage = new ProcessorPackageImpl();

    private Packer() {
    }

    public static String pack(String filePath) throws APIException {

        isValidPath(filePath);

        List<PackageVO> packagesFromFile = buildPackage.getPackagesFromFile(filePath);

        StringBuffer indexesFromPackage = processorPackage.getIndexesFromPackage(packagesFromFile);

        int lastIndexOfLineSeparator = indexesFromPackage.lastIndexOf(System.lineSeparator());

        String indexes = removeLastLineSeparator(indexesFromPackage, lastIndexOfLineSeparator);

        return indexes;
    }

    private static String removeLastLineSeparator(StringBuffer indexesFromPackage, int lastIndexOfLineSeparator) {
        return indexesFromPackage.substring(0, lastIndexOfLineSeparator);
    }

    private static void isValidPath(String path) throws APIException {

        try {
            if (path.isEmpty()) {
                throw new APIException("Empty path:" + path);
            }

            File file = new File(path);
            if (!file.exists())
                throw new APIException("File does not exists in path:" + path);

            if (file.length() == 0)
                throw new APIException("File should not be empty in path:" + path);

        } catch (InvalidPathException | NullPointerException ex) {
            throw new APIException("Error when read path:" + path, ex);
        }
    }

}
