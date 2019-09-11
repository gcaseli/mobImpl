package com.mobiquityinc.entities.constraints;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;

public class PackageItemsConstraints implements Constraints<PackageVO> {

    @Override
    public void validate(PackageVO packageVO) throws APIException {
        if (packageVO.getItems().size() > COUNT_MAX_ITEM)
            throw new APIException("items count cannot be more than " + COUNT_MAX_ITEM);

    }

}
