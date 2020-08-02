package com.example.o2o.controller.shopadmin;

import com.example.o2o.dto.ShopExecution;
import com.example.o2o.entity.Area;
import com.example.o2o.entity.PersonInfo;
import com.example.o2o.entity.Shop;
import com.example.o2o.entity.ShopCategory;
import com.example.o2o.service.AreaService;
import com.example.o2o.service.ShopCategoryService;
import com.example.o2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @GetMapping("/getshopinitinfo")
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        List<ShopCategory> shopCategoryList;
        List<Area> areaList;
        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modelMap.put("shopCategoryList", shopCategoryList);
            modelMap.put("areaList", areaList);
            modelMap.put("success", true);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
    }

//    @PostMapping("/registshop")
//    private Map<String, Object> registShop(HttpServletRequest request) {
//        // 1. 接收并转化相应的参数，包括店铺信息以及图片信息
//        // 2. 注册店铺
//        // 3. 返回结果
//
//        Map<String, Object> modelMap = new HashMap<>();
//
//        // 验证验证码
//        if (!CodeUtil.checkVerifyCode(request)) {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "验证码错误");
//            return modelMap;
//        }
//
//        // 1. 接收并转化相应的参数，包括店铺信息以及图片信息
//        // 前端的表单是打包成JSON字符串发过来的
//        // 要先接收  然后将json转化成Shop对象(by jackson.databind)
//        // 再提取参数，包括店铺信息以及图片信息
//        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
//
//        // 通过jackson-databind将前端传过来的json字符串转为Shop对象
//        ObjectMapper mapper = new ObjectMapper();
//        Shop shop = null;
//        try {
//            shop = mapper.readValue(shopStr, Shop.class);
//        } catch (Exception e) {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", e.getMessage());
//            return modelMap;
//        }
//
//        // 接收图片 使用Spring自带的CommonsMultipartFile
//        CommonsMultipartFile shopImg = null;
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
//                request.getSession().getServletContext()
//        );
//
//        // 判断是否有上传的文件流
//        if (commonsMultipartResolver.isMultipart(request)) {
//            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//            // 提取出上传文件流
//            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
//        } else {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "上传图片不能为空");
//            return modelMap;
//        }
//
//        // 2.注册店铺
//        // 注册店铺：处理图片
//        if (shop != null && shopImg != null) {
//            //Session TODO
//            PersonInfo owner = new PersonInfo();
//            owner.setUserId(1L);
//            shop.setOwner(owner);
//            shop.setOwnerId(owner.getUserId());
//
////            File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
////
////            // 接收的是Spring的CommonsMultipartFile 要转成File
////            try {
////                boolean f = shopImgFile.createNewFile();
////            } catch (IOException e) {
////                modelMap.put("success", false);
////                modelMap.put("errMsg", e.getMessage());
////                return modelMap;
////            }
////            try {
////                inputStreamToFile(shopImg.getInputStream(),shopImgFile);
////            } catch (IOException e) {
////                modelMap.put("success", false);
////                modelMap.put("errMsg", e.getMessage());
////                return modelMap;
////            }
//
//
//            ShopExecution se = null;
//            try {
//                se = shopService.addShopPlus(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
//                if (se.getState() == ShopStateEnum.CHECK.getState()) {
//                    modelMap.put("success", true);
//                }
//                else {
//                    modelMap.put("success", false);
//                    modelMap.put("errMsg", se.getStateInfo());
//                }
//            } catch (ShopOperationException | IOException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.getMessage());
//            }
//            return modelMap;
//
//        } else {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "请输入店铺信息");
//            return modelMap;
//        }
//    }

    private static void inputStreamToFile(InputStream inputStream, File file) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0, bytesRead);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用inputStreamToFile产生异常" + e.getMessage());
        } finally {
            try {
                //关闭输入输出流
                if (outputStream != null) {
                    outputStream.close();;
                }
                if (inputStream != null) {
                    inputStream.close();;
                }
            } catch (IOException ex) {
                throw new RuntimeException("inputStreamToFile关闭IO导致异常" + ex.getMessage());
            }
        }
    }

    @GetMapping("/getshopbyid")
    private Map<String, Object> getShopById(@RequestParam("shopId") Long shopId) {
        Map<String, Object> modelMap = new HashMap<>();
        if (shopId > -1) {
            try {
                Shop shop = shopService.getByShopId(shopId);
                List<Area> areaList = areaService.getAreaList();
                modelMap.put("shop", shop);
                modelMap.put("areaList", areaList);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success",false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

//    @RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
//    @ResponseBody
//    private Map<String, Object> modifyShop(HttpServletRequest request) {
//        Map<String, Object> modelMap = new HashMap<>();
//
//        // 验证验证码
//        if (!CodeUtil.checkVerifyCode(request)) {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "验证码错误");
//            return modelMap;
//        }
//        // 前端的表单是打包成JSON字符串发过来的
//        // 要先接收  然后将json转化成Shop对象(by jackson.databind)
//        // 再提取参数，包括店铺信息以及图片信息
//        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
//        ObjectMapper mapper = new ObjectMapper();
//        Shop shop = null;
//
//        try {
//            shop = mapper.readValue(shopStr, Shop.class);
//        } catch (Exception e) {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", e.getMessage());
//            return modelMap;
//        }
//        // 接收图片 使用Spring自带的CommonsMultipartFile
//        CommonsMultipartFile shopImg = null;
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
//                request.getSession().getServletContext()
//        );
//        if (commonsMultipartResolver.isMultipart(request)) {
//            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
//        }
//
//        // 修改店铺信息
//        if (shop != null && shop.getShopId() != null) {
//            ShopExecution se = null;
//            try {
//                if (shopImg == null) {
//                    se = shopService.modifyShop(shop, null, null);
//                } else {
//                    se = shopService.modifyShop(shop, shopImg.getInputStream(), shopImg.getOriginalFilename());
//                }
//                if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
//                    modelMap.put("success", true);
//                } else {
//                    modelMap.put("success", false);
//                    modelMap.put("errMsg", se.getStateInfo());
//                }
//            } catch (ShopOperationException | IOException e) {
//                modelMap.put("success", false);
//                modelMap.put("errMsg", e.getMessage());
//            }
//            return modelMap;
//        } else {
//            modelMap.put("success", false);
//            modelMap.put("errMsg", "请输入店铺Id");
//            return modelMap;
//        }
//    }

    @GetMapping("/getshoplist")
    private Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        // TO-DO
        PersonInfo user = new PersonInfo();
        user.setUserId(1L);
        user.setName("owner");
        request.getSession().setAttribute("user", user);
        user = (PersonInfo) request.getSession().getAttribute("user");

        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(user);
            shopCondition.setOwnerId(user.getUserId());
            ShopExecution se = shopService.getShopList(shopCondition, 0, 100);
            modelMap.put("shopList", se.getShopList());
            modelMap.put("user", user);
            modelMap.put("count", se.getCount());
            modelMap.put("success", true);
        }
        catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        return modelMap;
    }

//    @RequestMapping(value = "/setcurrentshopsession", method = RequestMethod.GET)
//    @ResponseBody
//    private Map<String, Object> setCurrentShopSession(HttpServletRequest request) {
//        Map<String, Object> modelMap = new HashMap<>();
//        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
//        if (shopId <= 0) {
//            // 如果前端没有传shopId过来，则尝试从session中获取，否则重定向回到shopList
//            Object currentShopObj = request.getSession().getAttribute("currentShop");
//            if (currentShopObj == null) {
//                modelMap.put("redirect", true);
//                modelMap.put("url", "/o2o/shopadmin/shoplist");
//            } else {
//                Shop currentShop = (Shop) currentShopObj;
//                modelMap.put("redirect", false);
//                modelMap.put("shopId", currentShop.getShopId());
//            }
//        }
//        else {
//            Shop currentShop = new Shop();
//            currentShop.setShopId(shopId);
//            request.getSession().setAttribute("currentShop", currentShop);
//            modelMap.put("redirect", false);
//        }
//        return modelMap;
//    }



}
