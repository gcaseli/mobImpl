package com.mobiquityinc.entities;

import com.mobiquityinc.entities.constraints.ConstrainsFactory;
import com.mobiquityinc.entities.constraints.Constraints;
import com.mobiquityinc.exception.APIException;

/*
    This class encapsulate data items inside the package
 */
public class ItemVO {

    private Integer index;

    private Double weight;

    private Double cost;

    public ItemVO(Integer index, Double weight, Double cost) throws APIException {
        this.index = index;
        this.weight = weight;
        this.cost = cost;

        validateItem();
    }

    private void validateItem() throws APIException {
        for (Constraints constraint : ConstrainsFactory.itemsConstrains()) {
            constraint.validate(this);
        }
    }

    public Integer getIndex() {
        return index;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }
}
