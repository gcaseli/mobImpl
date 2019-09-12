package com.mobiquityinc.entities.constraints;

import com.mobiquityinc.entities.ItemVO;
import com.mobiquityinc.exception.APIException;

/*
    Single Responsibility Pattern to validate a weight of ItemVO
 */
public class ItemWeightConstraints implements Constraints<ItemVO> {

    @Override
    public void validate(ItemVO item) throws APIException {
        if (item.getWeight() > WEIGHT_MAX_ITEM)
            throw new APIException("item weight cannot be more than " + WEIGHT_MAX_ITEM);

    }
}
