package com.mobiquityinc.entities.constraints;

import com.mobiquityinc.entities.ItemVO;
import com.mobiquityinc.entities.PackageVO;
import java.util.ArrayList;
import java.util.List;

/*
    This interface use Factory Pattern and have static methods that will return the list of constrains
 */
public interface ConstrainsFactory {

    static List<Constraints<ItemVO>> itemsConstrains() {
        List<Constraints<ItemVO>> constraints = new ArrayList<>();

        constraints.add(new ItemCostConstraints());
        constraints.add(new ItemWeightConstraints());

        return constraints;
    }

    static List<Constraints<PackageVO>> packageConstrains() {
        List<Constraints<PackageVO>> constraints = new ArrayList<>();

        constraints.add(new PackageWeightConstraints());
        constraints.add(new PackageItemsConstraints());

        return constraints;
    }
}
