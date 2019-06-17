package com.imooc.order.controller;

import com.imooc.order.client.ProductClient;
import com.imooc.order.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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


}
