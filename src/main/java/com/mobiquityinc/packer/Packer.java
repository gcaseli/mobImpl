package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import java.io.File;
import java.nio.file.InvalidPathException;

public class Packer {

    private Packer() {
    }

    public static void main(String[] args) {


    }

    public static String pack(String filePath) throws APIException {

        isValidPath(filePath);

        return "";
    }

    private static void isValidPath(String path) throws APIException {

        try {
            if (path.isEmpty()) {
                throw new APIException("Empty path:" + path);
            }

            File file = new File(path);
            if (!file.exists()) {
                throw new APIException("File does not exists in path:" + path);
            }
        } catch (InvalidPathException | NullPointerException ex) {
            throw new APIException("Error when read path:" + path, ex);
        }
    }

}
