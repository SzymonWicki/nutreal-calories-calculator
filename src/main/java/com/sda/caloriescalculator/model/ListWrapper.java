package com.sda.caloriescalculator.model;

import com.sda.caloriescalculator.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class ListWrapper {

    private List<ProductEntity> productEntityList = new ArrayList<>();

    public List<ProductEntity> getProductEntityList() {
        return productEntityList;
    }

    public void setProductEntityList(List<ProductEntity> productEntityList) {
        this.productEntityList = productEntityList;
    }
}
