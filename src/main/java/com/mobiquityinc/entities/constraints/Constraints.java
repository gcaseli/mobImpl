package com.mobiquityinc.entities.constraints;

import com.mobiquityinc.exception.APIException;

/*
    This interface has a generic method to validate any kid of object
 */
@FunctionalInterface
public interface Constraints<T> {

    double MAX_PACKAGE_WEIGHT = 100.00;

    double WEIGHT_MAX_ITEM = 100.00;

    double COST_MAX_ITEM = 100.00;

    double COUNT_MAX_ITEM = 15;

    void validate(T object) throws APIException;
}
