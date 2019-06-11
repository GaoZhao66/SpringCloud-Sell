package com.imooc.product.VO;

import lombok.Data;

@Data
public class ResultVO<T> {

    /**错误码*/
    private Integer code;
    /**错误信息*/
    private String msg;
    /**返回结果*/
    private T data;

}
