package com.mobiquityinc.entities;

import java.util.List;

public class PackageVO {

    private Integer weightPackage;

    private List<ThingVO> things;

    public PackageVO(Integer weightPackage, List<ThingVO> things) {
        this.weightPackage = weightPackage;
        this.things = things;
    }

    public Integer getWeightPackage() {
        return weightPackage;
    }

    public List<ThingVO> getThings() {
        return things;
    }
}
