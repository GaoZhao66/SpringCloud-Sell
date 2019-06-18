package product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import product.dataobject.ProductInfo;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

     /**
      * 查询所有在架商品
      * */
     List<ProductInfo>  findByProductStatus(Integer productStatus);

     /**
      * 根据productId集合 查询商品信息
      * */
     List<ProductInfo> findByProductIdIn(List<String> productIdList);


}
