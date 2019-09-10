package com.mobiquityinc.entities;

import java.math.BigDecimal;

public class ThingVO {

    private Integer index;

    private Double weigth;

    private BigDecimal cost;

    public ThingVO(Integer index, Double weigth, BigDecimal cost) {
        this.index = index;
        this.weigth = weigth;
        this.cost = cost;
    }
}
