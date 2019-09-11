package com.mobiquityinc.usecases.filter;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.entities.ItemVO;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterPackageImpl implements FilterPackage {

    @Override
    public StringBuffer getIndexesFromPackage(List<PackageVO> packages) {

        StringBuffer sb = new StringBuffer();

        packages.forEach(pack -> {

            List<ItemVO> thingsSorted = sortThings(pack);

            if (thingsSorted.isEmpty()) {
                sb.append("-" + System.lineSeparator());
                return;
            }

            ItemVO thingFirstElement = thingsSorted.get(0);

            String indexUsed = thingFirstElement.getIndex().toString();

            Double totalWeight = thingFirstElement.getWeight();

            for (int i = 1; i < thingsSorted.size(); i++) {

                ItemVO thingNextElement = thingsSorted.get(i);

                if (totalWeight + thingNextElement.getWeight() <= pack.getPackageWeight()) {
                    totalWeight = sumWeight(totalWeight, thingNextElement);
                    indexUsed = concatIndex(indexUsed, thingNextElement);
                }

            }
            sb.append(indexUsed + System.lineSeparator() );

        });

        return sb;
    }

    private String concatIndex(String indexUsed, ItemVO thingNextElement) {
        indexUsed += "," + thingNextElement.getIndex().toString();
        return indexUsed;
    }

    private Double sumWeight(Double totalWeight, ItemVO thingNextElement) {
        totalWeight += thingNextElement.getWeight();
        return totalWeight;
    }

    private List<ItemVO> sortThings(PackageVO pack) {
        return pack.getItems()
                .stream()
                .filter(filterByWeight(pack))
                .sorted(orderByCostAndWeight())
                .collect(Collectors.toList());
    }

    private Predicate<ItemVO> filterByWeight(PackageVO pack) {
        return p -> p.getWeight() <= pack.getPackageWeight();
    }

    private Comparator<ItemVO> orderByCostAndWeight() {
        return Comparator.comparing(ItemVO::getCost).reversed()
                .thenComparing(ItemVO::getWeight);
    }


}
