package product.service;

import product.dataobject.ProductInfo;
import product.dto.CartDTO;

import java.util.List;

public interface ProductService {


    /**
     * 查询所有在架商品
     * */
    List<ProductInfo> findUpAll();

    /**
     * 根据productId集合 查询商品信息
     * */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     * */
    void decreaseStock(List<CartDTO> cartDTOList);


}
