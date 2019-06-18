package com.imooc.order.controller;

import com.imooc.order.client.ProductClient;
import com.imooc.order.dataobject.ProductInfo;
import com.imooc.order.dto.CartDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 调用商品服务 测试用
 * */


@RestController
@Slf4j
public class ClientController {

      @Autowired
      private ProductClient productClient;


      @GetMapping("/getProcuctMsg")
      public String getProcuctMsg(){
          String response = productClient.productMsg();
          log.info("response={}",response);
          return response;
      }

      @GetMapping("/getProductList")
      public String getProductList(){
          List<ProductInfo> productInfoList = productClient.listForOrder(Arrays.asList("157875196366160022"));
          log.info("response={}" + productInfoList);
          return "OK";
      }

      @GetMapping("productDecreaseStock")
      public String productDecreaseStock(){
          productClient.decreaseStock(Arrays.asList(new CartDTO("157875196366160022",3)));
          return "ok";
      }




}
