package com.example.o2o.util;

import com.example.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ImageUtil {

    // 时间格式化的格式
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
    // 生成随机数
    private static final Random r = new Random();

    /**
     * 接收前端上传的图片，按店铺id修改图片名并保存为缩略图
     * @param shopImg springmvc接收的图片上传
     * @param targetAddr 文件保存路径
     * @return 文件相对路径
     */
    public static String generateThumbnail(File shopImg, String targetAddr) {
        // 为图片生成随机图片名
        String realFileName = getRandomFileName();
        // 获取图片扩展名
        String extension = getFileExtension(shopImg.getName());
        // 创建图片目录
        makeDirPath(targetAddr);
        // 获取图片相对路径
        String relativeAddr = targetAddr + realFileName + extension;

        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(shopImg).
                    size(200, 200).
                    outputQuality(0.25f).
                    toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static String generateThumbnailPlus(InputStream shopImgInputStream, String fileName, String targetAddr) {
        // 为图片生成随机图片名
        String realFileName = getRandomFileName();
        // 获取图片扩展名
        String extension = getFileExtension(fileName);
        // 创建图片目录
        makeDirPath(targetAddr);
        // 获取图片相对路径
        String relativeAddr = targetAddr + realFileName + extension;

        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(shopImgInputStream).
                    size(200, 200).
                    outputQuality(0.25f).
                    toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    public static String generateThumbnailImg(ImageHolder thumbnail, String targetAddr) {
        String realFileName = getRandomFileName();
        String extension = getFileExtension(thumbnail.getImgName());
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getImg()).size(200, 200).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }


    /**
     * 生成随机文件名 :当前年月日小时分钟秒+五位随机数
     * @return 文件名
     */
    public static String getRandomFileName() {
        int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
        String nowTimeStr = sDateFormat.format(new Date()); // 当前时间
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流扩展名
     * @param fileName 文件名
     * @return 扩展名
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 创建文件目录
     * @param targetAddr 文件地址
     */
    private static void makeDirPath(String targetAddr) {
        // 绝对路径
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;

        File dirPath = new File(realFileParentPath);

        if (!dirPath.exists()) {
            // 递归地创建
            boolean f = dirPath.mkdirs();
        }
    }

    /**
     * storePath是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除该文件
     * 如果storePath是目录路径则删除该目录下的所有文件
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                for (File file : files) {
                    file.delete();
                }
            }
            fileOrPath.delete();
        }
    }

    /**
     * 添加多张商品详情图
     */
    public static List<String> generateNormalImgs(List<ImageHolder> imgs, String targetAddr) {
        int count = 0;
        List<String> relativeAddrList = new ArrayList<String>();
        if (imgs != null && imgs.size() > 0) {
            makeDirPath(targetAddr);
            for (ImageHolder img : imgs) {
                String realFileName = getRandomFileName();
                String extension = getFileExtension(img.getImgName());
                String relativeAddr = targetAddr + realFileName + count + extension;
                File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
                count++;
                try {
                    Thumbnails.of(img.getImg()).size(600, 300).outputQuality(0.5f).toFile(dest);
                } catch (IOException e) {
                    throw new RuntimeException("创建图片失败：" + e.toString());
                }
                relativeAddrList.add(relativeAddr);
            }
        }
        return relativeAddrList;
    }
}
