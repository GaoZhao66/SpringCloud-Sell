package com.imooc.product.repository;

import com.imooc.product.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

     /**查询所有在架商品*/
     List<ProductInfo>  findByProductStatus(Integer productStatus);

}
