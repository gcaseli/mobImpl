package com.mobiquityinc.entities;

import java.math.BigDecimal;

public class ThingVO {

    private Integer index;

    private Double weight;

    private BigDecimal cost;

    public ThingVO(Integer index, Double weight, BigDecimal cost) {
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

    public BigDecimal getCost() {
        return cost;
    }
}
