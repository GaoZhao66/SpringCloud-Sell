package com.imooc.product.repository;

import com.imooc.product.ProductApplicationTests;
import com.imooc.product.dataobject.ProductCategory;
import com.imooc.product.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;


public class ProductInfoRepositoryTest extends ProductApplicationTests {

    @Autowired
    private ProductInfoRepository productInfoRepository;



    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(0);
        System.out.println(productInfoList);
    }




}