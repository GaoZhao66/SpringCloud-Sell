package order.controller;

import lombok.extern.slf4j.Slf4j;
import order.converter.OrderForm2OrderDTOConverter;
import order.dto.OrderDTO;
import order.enums.ResultEnum;
import order.exception.SellException;
import order.from.OrderForm;
import order.service.OrderService;
import order.utils.ResultVOUtil;
import order.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/buyer/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

   /**
    * 1.参数校验
    * 2.查询商品信息（调用商品服务）
    * 3.计算总价
    * 4.扣库存
    * 5.订单入库
    * */
   @PostMapping("create")
   public ResultVO<Map<String,String>> create(@Valid OrderForm orderFrom, BindingResult  bindingResult){

      if(bindingResult.hasErrors()){
          log.error("【创建订单】参数不正确,orderForm={}",orderFrom);
          throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
      }

       OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderFrom);
       if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
           log.error("【创建订单】购物车不能为空");
           throw  new SellException(ResultEnum.CART_EMPTY);
       }
       OrderDTO createResult = orderService.create(orderDTO);
       Map<String,String> map = new HashMap<>();
       map.put("orderId",createResult.getOrderId());
       return ResultVOUtil.success(map);
   }




}
