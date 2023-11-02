package com.msrfyl.k24.resource.utility;

import com.msrfyl.k24.resource.general.HandleException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Base64;
import java.util.UUID;

@Service
public class Utility {
    private Logger logger = LoggerFactory.getLogger(Utility.class);

    public String randomUUID(int take) {
        return UUID.randomUUID().toString().toUpperCase().substring(take);
    }

    private void createDirectory(String path) {
        File files = new File(path);
        if (!files.exists()) {
            files.mkdirs();
        }
    }

    public String saveImagesToDirectory(String imgString, String path) {
        String filename = randomUUID(8);
        byte[] img = Base64.getDecoder().decode(imgString);
        if (img.length > 1000000) {
            throw new HandleException("Maximum size file is 1 MB");
        }
        try {
            createDirectory("assets/" + path);
            String dir = "assets/" + path + "/" + filename + ".png";
            FileUtils.writeByteArrayToFile(new File(dir), img);
            logger.info("upload images to directory " + dir);
            return dir;
        } catch (Exception e) {
            logger.error("failed upload image", e);
            return "";
        }
    }

}
