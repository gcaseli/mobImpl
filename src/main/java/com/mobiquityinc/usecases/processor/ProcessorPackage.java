package com.mobiquityinc.usecases.processor;

import com.mobiquityinc.entities.PackageVO;
import java.util.List;

public interface ProcessorPackage {

    StringBuffer getIndexesFromPackage(List<PackageVO> packages);

}
