package com.tian.spring.spel.model;

import java.util.Map;

public class ShopInfo {

    private Long brandId;

    private String shopName;

    private Long shopId;

    private String brandName;

    private DishDetail dishDetail;


    public DishDetail getDishDetail() {
        return dishDetail;
    }

    public void setDishDetail(DishDetail dishDetail) {
        this.dishDetail = dishDetail;
    }

    private Map<String, String> mapData;

    public Map<String, String> getMapData() {
        return mapData;
    }

    public void setMapData(Map<String, String> mapData) {
        this.mapData = mapData;
    }

    public ShopInfo() {
    }

    public ShopInfo(Long brandId, Long shopId) {
        this.brandId = brandId;
        this.shopId = shopId;
    }

    public ShopInfo(Long brandId, String shopName, Long shopId, String brandName) {
        this.brandId = brandId;
        this.shopName = shopName;
        this.shopId = shopId;
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
