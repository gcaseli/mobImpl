package com.mobiquityinc.entities.constraints;

import com.mobiquityinc.entities.ItemVO;
import com.mobiquityinc.exception.APIException;

public class ItemCostConstraints implements Constraints<ItemVO> {

    @Override
    public void validate(ItemVO item) throws APIException {
        if (item.getCost() > COST_MAX_ITEM)
            throw new APIException("item cost cannot be more than " + COST_MAX_ITEM);

    }
}
