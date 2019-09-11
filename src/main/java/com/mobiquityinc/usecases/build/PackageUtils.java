package com.mobiquityinc.usecases.build;

import com.mobiquityinc.entities.ItemVO;
import com.mobiquityinc.entities.PackageVO;
import com.mobiquityinc.exception.APIException;
import java.util.ArrayList;
import java.util.List;

/*
    This class is responsible to transform lines to object
 */
public class PackageUtils {

    public PackageVO lineToPackage(String[] content) {

        List<ItemVO> itemVOS = new ArrayList<>();

        try {

            String packageWeight = content[0];

            String[] packagesByLine = content[1].split(" ");

            for (int i = 1; i < packagesByLine.length; i++) {

                String[] things = getItems(packagesByLine[i]);

                if (isItemLengthNotValid(things)) {
                    throw new RuntimeException("Items must contains 3 values:" + things);
                }

                buildItems(itemVOS, things);
            }
            return new PackageVO(Double.valueOf(packageWeight.trim()), itemVOS);
        } catch (APIException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private String[] getItems(String s) {
        String packages = removeParentheses(s, "[\\(||\\)]");

        return packages.split(",");
    }

    private String removeParentheses(String s, String s2) {
        return s.replaceAll(s2, "");
    }

    private boolean isItemLengthNotValid(String[] items) {
        return items.length != 3;
    }

    private void buildItems(List<ItemVO> itemsVO, String[] items) throws APIException {
        Integer index = Integer.valueOf(items[0]);

        Double weight = Double.valueOf(items[1]);

        Double cost = Double.valueOf(removeParentheses(items[2], "[^\\d.]"));

        ItemVO itemVO = new ItemVO(index, weight, cost);

        itemsVO.add(itemVO);
    }
}
