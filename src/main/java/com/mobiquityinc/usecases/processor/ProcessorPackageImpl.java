package com.mobiquityinc.usecases.processor;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.entities.ItemVO;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
    This class is responsible to return withs indexes can be inside the package
 */
public class ProcessorPackageImpl implements ProcessorPackage {

    @Override
    public StringBuffer getIndexesFromPackage(List<PackageVO> packages) {

        StringBuffer sb = new StringBuffer();

        packages.forEach(pack -> {

            List<ItemVO> itemsSorted = sortItems(pack);

            if (itemsSorted.isEmpty()) {
                sb.append("-" + System.lineSeparator());
                return;
            }

            ItemVO itemFirstElement = itemsSorted.get(0);

            String indexUsed = itemFirstElement.getIndex().toString();

            Double totalWeight = itemFirstElement.getWeight();

            for (int i = 1; i < itemsSorted.size(); i++) {

                ItemVO thingNextElement = itemsSorted.get(i);

                double sumWeight = totalWeight + thingNextElement.getWeight();

                if (isSumWeightValid(pack, sumWeight)) {
                    totalWeight = sumWeight(totalWeight, thingNextElement);
                    indexUsed = concatIndex(indexUsed, thingNextElement);
                }

            }
            sb.append(indexUsed + System.lineSeparator() );

        });

        return sb;
    }

    private boolean isSumWeightValid(PackageVO pack, double sumWeight) {
        return sumWeight <= pack.getPackageWeight();
    }

    private String concatIndex(String indexUsed, ItemVO thingNextElement) {
        indexUsed += "," + thingNextElement.getIndex().toString();
        return indexUsed;
    }

    private Double sumWeight(Double totalWeight, ItemVO thingNextElement) {
        totalWeight += thingNextElement.getWeight();
        return totalWeight;
    }

    private List<ItemVO> sortItems(PackageVO pack) {
        return pack.getItems()
                .stream()
                .filter(filterByWeight(pack))
                .sorted(orderByCostAndWeight())
                .collect(Collectors.toList());
    }

    private Predicate<ItemVO> filterByWeight(PackageVO pack) {
        return p -> isSumWeightValid(pack, p.getWeight());
    }

    private Comparator<ItemVO> orderByCostAndWeight() {
        return Comparator.comparing(ItemVO::getCost).reversed()
                .thenComparing(ItemVO::getWeight);
    }


}
