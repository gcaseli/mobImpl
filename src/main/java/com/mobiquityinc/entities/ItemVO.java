package com.mobiquityinc.entities;

/*
    This class encapsulate data items inside the package
 */
public class ItemVO {

    private Integer index;

    private Double weight;

    private Double cost;

    public ItemVO(Integer index, Double weight, Double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
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
