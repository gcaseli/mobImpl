package com.mobiquityinc.usecases;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.entities.ThingVO;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterPackageImpl implements FilterPackage {

    @Override
    public StringBuffer getIndexesFromPackage(List<PackageVO> packages) {

        StringBuffer sb = new StringBuffer();

        packages.forEach(pack -> {

            List<ThingVO> thingsSorted = sortThings(pack);

            if (thingsSorted.isEmpty()) {
                sb.append("-" + System.lineSeparator());
                return;
            }

            ThingVO thingFirstElement = thingsSorted.get(0);

            String indexUsed = thingFirstElement.getIndex().toString();

            Double totalWeight = thingFirstElement.getWeight();

            for (int i = 1; i < thingsSorted.size(); i++) {

                ThingVO thingNextElement = thingsSorted.get(i);

                if (totalWeight + thingNextElement.getWeight() <= pack.getWeightPackage()) {
                    totalWeight = sumWeight(totalWeight, thingNextElement);
                    indexUsed = concatIndex(indexUsed, thingNextElement);
                }

            }
            sb.append(indexUsed + System.lineSeparator() );

        });

        return sb;
    }

    private String concatIndex(String indexUsed, ThingVO thingNextElement) {
        indexUsed += "," + thingNextElement.getIndex().toString();
        return indexUsed;
    }

    private Double sumWeight(Double totalWeight, ThingVO thingNextElement) {
        totalWeight += thingNextElement.getWeight();
        return totalWeight;
    }

    private List<ThingVO> sortThings(PackageVO pack) {
        return pack.getThings()
                .stream()
                .filter(filterByWeight(pack))
                .sorted(orderByCostAndWeight())
                .collect(Collectors.toList());
    }

    private Predicate<ThingVO> filterByWeight(PackageVO pack) {
        return p -> p.getWeight() <= pack.getWeightPackage();
    }

    private Comparator<ThingVO> orderByCostAndWeight() {
        return Comparator.comparing(ThingVO::getCost).reversed()
                .thenComparing(ThingVO::getWeight);
    }


}
