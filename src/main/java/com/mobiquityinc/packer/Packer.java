package com.mobiquityinc.packer;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.usecases.filter.FilterPackage;
import com.mobiquityinc.usecases.filter.FilterPackageImpl;
import com.mobiquityinc.usecases.read.ReaderPackage;
import com.mobiquityinc.usecases.read.ReaderPackageImpl;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.util.List;

public class Packer {

    private static ReaderPackage readerPackage = new ReaderPackageImpl();
    private static FilterPackage filterPackage = new FilterPackageImpl();

    private Packer() {
    }

    public static String pack(String filePath) throws APIException {

        isValidPath(filePath);

        List<PackageVO> packagesFromFile = readerPackage.getPackagesFromFile(filePath);
        StringBuffer indexesFromPackage = filterPackage.getIndexesFromPackage(packagesFromFile);

        int lastIndexOfLineSeparator = indexesFromPackage.lastIndexOf(System.lineSeparator());

        String indexes = indexesFromPackage.substring(0, lastIndexOfLineSeparator);

        return indexes;
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
