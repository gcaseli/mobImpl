package com.mobiquityinc.usecases.build;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import java.util.List;

public interface BuildPackage {

    List<PackageVO> getPackagesFromFile(String pathFile) throws APIException;

}
