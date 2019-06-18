package com.imooc.order.client;

import com.imooc.order.dataobject.ProductInfo;
import com.imooc.order.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {

    @GetMapping("/msg")
    String productMsg();

    /**
     * 根据productId集合 查询商品信息
     * */
    @PostMapping("/product/listForOrder")
    public List<ProductInfo> listForOrder(List<String> productIdList);

    /**
     * 扣库存
     * */
    @PostMapping("/product/decreaseStock")
    public void decreaseStock(List<CartDTO> cartDTOList);

}
