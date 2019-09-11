package com.mobiquityinc.usecases.filter;

import com.mobiquityinc.entities.PackageVO;
import java.util.List;

public interface FilterPackage {

    StringBuffer getIndexesFromPackage(List<PackageVO> packages);

}
