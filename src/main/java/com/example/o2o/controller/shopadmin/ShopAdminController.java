package com.example.o2o.controller.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {

    @RequestMapping(value = "/shopoperation")
    private String shopOperation() {
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    private String shopList() {
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanagement")
    private String shopManagement() {
        return "shop/shopmanagement";
    }

    @RequestMapping(value = "/productcategorylist")
    private String productCategoryManagement() {
        return "shop/productcategorylist";
    }

    @RequestMapping(value = "/productlist")
    private String productList() { return "shop/productlist"; }

    @RequestMapping(value = "/productedit")
    private String productEdit() { return "shop/productedit"; }

    @RequestMapping(value = "/ownerregister")
    private String ownerRegister() { return "shop/ownerregister"; }

    @RequestMapping(value = "/ownerlogin")
    private String ownerLogin() { return "shop/ownerlogin"; }

}
