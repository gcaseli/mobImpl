package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.sun.xml.internal.ws.util.StringUtils;
import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

public class Packer {

    private Packer() {
    }

    public static void main(String[] args) {


    }

    public static String pack(String filePath) throws APIException {

        if (isValidPath(filePath)){
            return "";
        }
        return "";
    }


    public static boolean isValidPath(String path) throws APIException {
        try {
            if(path.isEmpty()){
                throw new APIException("Empty path:" + path);
            }

            File file = new File(path);
            if(!file.exists()){
                throw new APIException("File does not exists in path:" + path);
            }
        } catch (InvalidPathException | NullPointerException ex) {
            throw new APIException("Error when read path:" + path, ex);
        }
        return true;
    }

}
