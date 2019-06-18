package com.imooc.product.client;

import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "product")
public interface ProductClient {


    /**
     * 根据productId集合 查询商品信息
     * */
    @PostMapping("/product/listForOrder")
    public List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 扣库存
     * */
    @PostMapping("/product/decreaseStock")
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);

}
