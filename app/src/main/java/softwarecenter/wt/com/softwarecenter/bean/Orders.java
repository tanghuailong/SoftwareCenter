package softwarecenter.wt.com.softwarecenter.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by chenggong on 2016/10/11.
 */
@AllArgsConstructor
@Data
public class Orders {
    private String product_id;//产品编码
    private String product_desc;//产品名称
    private String order_id;//订单id
    private double order_ratio;//订单百分比
    private String order_start_time;//订单开始时间
    private String order_quantity;//产品数量
    private String order_status;//订单状态
}
