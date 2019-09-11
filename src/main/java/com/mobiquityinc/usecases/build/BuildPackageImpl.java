package com.mobiquityinc.usecases.build;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
    This class is responsible to return an object representing the file
 */
public class BuildPackageImpl implements BuildPackage {

    private PackageUtils packageUtils = new PackageUtils();

    public List<PackageVO> getPackagesFromFile(String pathFile) throws APIException {

        try {
            return Files
                    .lines(Paths.get(pathFile))
                    .map(mapLineToPackage).collect(Collectors.toList());
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            throw new APIException(e.getMessage());
        }
    }

    Function<String, PackageVO> mapLineToPackage = line -> {

        String[] content = line.split(":");

        return packageUtils.lineToPackage(content);
    };


}
