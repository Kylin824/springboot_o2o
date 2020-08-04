package com.example.o2o.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath="G:/upload/image/";
        } else {
            basePath="/home/ubuntu/project/upload/image/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImgPath(long shopId) {
        // "/upload/item/shop/x/"
        String imgPath = "item/shop/" + shopId + "/";
        imgPath = imgPath.replace("/", seperator);
        return imgPath;
    }
}
