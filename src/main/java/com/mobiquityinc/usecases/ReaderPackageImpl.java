package com.mobiquityinc.usecases;

import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.entities.ThingVO;
import com.mobiquityinc.exception.APIException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReaderPackageImpl implements ReaderPackage{

    public List<PackageVO> getPackagesFromFile(String pathFile) {

        try {
            return Files
                    .lines(Paths.get(pathFile))
                    .map(mapLineToPackage).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    Function<String, PackageVO> mapLineToPackage = line -> {

        List<ThingVO> thingsVO = new ArrayList<>();

        String[] split = line.split(":");

        String packageWeight = split[0];

        String[] packagesByLine = split[1].split(" ");

        for (int i = 1; i < packagesByLine.length; i++) {

            String[] things = getThings(packagesByLine[i]);

            if (isThingLengthNotValid(things)) {
                continue;
                //throw new APIException("Things must contains 3 values:" + things);
            }

            buildThing(thingsVO, things);
        }

        return new PackageVO(Double.valueOf(packageWeight.trim()), thingsVO);
    };

    private String[] getThings(String s) {
        String packages = s.replaceAll("[\\(||\\)]", "");

        return packages.split(",");
    }

    private boolean isThingLengthNotValid(String[] things) {
        return things.length != 3;
    }

    private void buildThing(List<ThingVO> thingsVO, String[] things) {
        Integer index = Integer.valueOf(things[0]);

        Double weight = Double.valueOf(things[1]);

        BigDecimal cost = new BigDecimal(things[2].replaceAll("[^\\d.]", ""));

        thingsVO.add(new ThingVO(index, weight, cost));
    }
}
