package com.mobiquityinc.entities.constraints;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;

/*
    Single Responsibility Pattern to validate a weight of PackageVO
 */
public class PackageWeightConstraints implements Constraints<PackageVO> {

    @Override
    public void validate(PackageVO packageVO) throws APIException {
        if (packageVO.getPackageWeight() > MAX_PACKAGE_WEIGHT)
            throw new APIException("package weight cannot be more than " + MAX_PACKAGE_WEIGHT);
    }
}
