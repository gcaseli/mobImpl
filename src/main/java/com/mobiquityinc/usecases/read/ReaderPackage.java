package com.mobiquityinc.usecases.read;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import java.util.List;

public interface ReaderPackage {

    List<PackageVO> getPackagesFromFile(String pathFile) throws APIException;

}
