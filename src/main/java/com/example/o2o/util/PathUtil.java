package com.example.o2o.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath="D:/code/javaweb/project/image/";
        } else {
            basePath="/javaweb/o2o/image/";
        }
        basePath = basePath.replace("/", seperator);
        return basePath;
    }

    public static String getShopImgPath(long shopId) {
        // "/upload/item/shop/x/"
        String imgPath = "upload/item/shop/" + shopId + "/";
        imgPath = imgPath.replace("/", seperator);
        return imgPath;
    }
}
