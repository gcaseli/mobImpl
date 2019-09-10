package com.mobiquityinc.entities;

import java.util.List;

public class PackageVO {

    private Double weightPackage;

    private List<ThingVO> things;

    public PackageVO(Double weightPackage, List<ThingVO> things) {
        this.weightPackage = weightPackage;
        this.things = things;
    }

    public Double getWeightPackage() {
        return weightPackage;
    }

    public List<ThingVO> getThings() {
        return things;
    }
}
