package com.mobiquityinc.usecases.read;

import com.mobiquityinc.entities.ItemVO;
import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.usecases.constraints.Constraints;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReaderPackageImpl implements ReaderPackage {

    private Constraints<ItemVO> itemConstraints = new ItemConstraints();

    public List<PackageVO> getPackagesFromFile(String pathFile) throws APIException {

        try {
            return Files
                    .lines(Paths.get(pathFile))
                    .map(mapLineToPackage).collect(Collectors.toList());
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            throw new APIException(e.getMessage());
        }
    }

    Function<String, PackageVO> mapLineToPackage = line -> {

        List<ItemVO> itemVOS = new ArrayList<>();

        String[] split = line.split(":");

        String packageWeight = split[0];

        String[] packagesByLine = split[1].split(" ");

        for (int i = 1; i < packagesByLine.length; i++) {

            String[] things = getItems(packagesByLine[i]);

            if (isItemLengthNotValid(things)) {
                throw new RuntimeException("Items must contains 3 values:" + things);
            }

            try {
                buildItems(itemVOS, things);
            } catch (APIException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }

        }

        return new PackageVO(Double.valueOf(packageWeight.trim()), itemVOS);
    };

    private String[] getItems(String s) {
        String packages = s.replaceAll("[\\(||\\)]", "");

        return packages.split(",");
    }

    private boolean isItemLengthNotValid(String[] items) {
        return items.length != 3;
    }

    private void buildItems(List<ItemVO> itemsVO, String[] items) throws APIException {
        Integer index = Integer.valueOf(items[0]);

        Double weight = Double.valueOf(items[1]);

        Double cost = Double.valueOf(items[2].replaceAll("[^\\d.]", ""));
        ItemVO itemVO = new ItemVO(index, weight, cost);
        itemConstraints.validate(itemVO);
        itemsVO.add(itemVO);
    }
}
