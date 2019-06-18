package product.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.dataobject.ProductInfo;
import product.dto.CartDTO;
import product.enums.ProductStatusEnum;
import product.enums.ResultEnum;
import product.exception.ProductException;
import product.repository.ProductInfoRepository;
import product.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductInfoRepository productInfoRepository;

    /**查询所有在架商品*/
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * 根据productId集合 查询商品信息
     * */
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoRepository.findByProductIdIn(productIdList);
    }

    /**
     * 扣库存
     * */
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            //查询商品
            Optional<ProductInfo> productInfoOptional = productInfoRepository.findById(cartDTO.getProductId());
            //判断商品是否存在
            if(!productInfoOptional.isPresent()){
                throw  new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //存在 判断商品够不够扣
            ProductInfo productInfo = productInfoOptional.get();
            //数据库中查到的库存 - 购物车中的库存
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0){
                throw  new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
