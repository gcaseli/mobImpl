package com.mobiquityinc.entities;

import com.mobiquityinc.entities.constraints.ConstrainsFactory;
import com.mobiquityinc.entities.constraints.Constraints;
import com.mobiquityinc.exception.APIException;
import java.util.List;

/*
    This class encapsulate data about the package
 */
public class PackageVO {

    private Double packageWeight;

    private List<ItemVO> items;

    public PackageVO(Double packageWeight, List<ItemVO> items) throws APIException {
        this.packageWeight = packageWeight;
        this.items = items;

        validatePackage();
    }

    private void validatePackage() throws APIException {
        for (Constraints constraint : ConstrainsFactory.packageConstrains()) {
            constraint.validate(this);
        }
    }

    public Double getPackageWeight() {
        return packageWeight;
    }

    public List<ItemVO> getItems() {
        return items;
    }
}
