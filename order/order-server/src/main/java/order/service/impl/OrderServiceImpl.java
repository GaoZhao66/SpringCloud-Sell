package order.service.impl;

import com.imooc.product.client.ProductClient;
import com.imooc.product.common.DecreaseStockInput;
import com.imooc.product.common.ProductInfoOutput;
import order.dataobject.OrderDetail;
import order.dataobject.OrderMaster;
import order.dto.OrderDTO;
import order.enums.OrderStatusEnum;
import order.enums.PayStatusEnum;
import order.repository.OrderDetailRepository;
import order.repository.OrderMasterRepository;
import order.service.OrderService;
import order.utils.Keyutil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;


    /**
     *  创建订单
     * */
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = Keyutil.genUniqueKey();

        //1.查询商品信息 （调用商品服务）
        List<String> produtIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());

        List<ProductInfoOutput> productInfoOutputList = productClient.listForOrder(produtIdList);

        //2.计算总价   单价 * 数量
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
                if(orderDetail.getProductId().equals(productInfoOutput.getProductId())){
                    orderAmout = productInfoOutput.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfoOutput,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(Keyutil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        //3.扣库存 （调用商品服务）
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);


        //4.订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        return orderDTO;
    }








}
