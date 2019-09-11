package com.mobiquityinc.entities;

import java.util.List;

/*
    This class encapsulate data about the package
 */
public class PackageVO {

    private Double packageWeight;

    private List<ItemVO> items;

    public PackageVO(Double packageWeight, List<ItemVO> items) {
        this.packageWeight = packageWeight;
        this.items = items;
    }

    public Double getPackageWeight() {
        return packageWeight;
    }

    public List<ItemVO> getItems() {
        return items;
    }
}
