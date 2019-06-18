package product.service;

import product.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {


    /***/
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);


}
