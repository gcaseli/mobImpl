package com.mobiquityinc.usecases;

import com.mobiquityinc.entities.PackageVO;
import java.util.List;

public interface ReaderPackage {

    List<PackageVO> getPackagesFromFile(String pathFile);

}
