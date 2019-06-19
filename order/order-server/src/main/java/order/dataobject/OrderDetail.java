package order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {

  @Id
  private String detailId;

  private String orderId;

  private String productId;

  /**商品名称*/
  private String productName;

  /**当前价格,单位分*/
  private BigDecimal productPrice;

  /**数量*/
  private Integer productQuantity;

  /**小图*/
  private String productIcon;

  private Date createTime;

  private Date updateTime;


}
