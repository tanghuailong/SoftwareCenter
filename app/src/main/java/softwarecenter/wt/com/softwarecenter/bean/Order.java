package softwarecenter.wt.com.softwarecenter.bean;

/**
 * Created by chogo on 2016/9/26.
 * entity of Order
 */

public class Order {
    private String order_id;
    private String name;//订单名称
    private String product_line;//生产线
    private int order_qty;//订单数量
    private String start_time;//开始时间
    private String end_date;//结束时间
    private String product;//产品
    private String status;//订单状态
    private int priority;//优先级
    private String  notes;//备注
    private String order_bom;//物料清单
    private boolean postpone_label;//是否可延迟订单
    private boolean ahead_time_label;//是否可提前

    public Order() {

    }
    public Order(String name, String product_line, int order_qty, String start_time, String end_date, String product, String status, int priority, String notes, String order_bom, boolean postpone_label, boolean ahead_time_label) {
        this.name = name;
        this.product_line = product_line;
        this.order_qty = order_qty;
        this.start_time = start_time;
        this.end_date = end_date;
        this.product = product;
        this.status = status;
        this.priority = priority;
        this.notes = notes;
        this.order_bom = order_bom;
        this.postpone_label = postpone_label;
        this.ahead_time_label = ahead_time_label;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_line() {
        return product_line;
    }

    public void setProduct_line(String product_line) {
        this.product_line = product_line;
    }

    public int getOrder_qty() {
        return order_qty;
    }

    public void setOrder_qty(int order_qty) {
        this.order_qty = order_qty;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getOrder_bom() {
        return order_bom;
    }

    public void setOrder_bom(String order_bom) {
        this.order_bom = order_bom;
    }

    public boolean isPostpone_label() {
        return postpone_label;
    }

    public void setPostpone_label(boolean postpone_label) {
        this.postpone_label = postpone_label;
    }

    public boolean isAhead_time_label() {
        return ahead_time_label;
    }

    public void setAhead_time_label(boolean ahead_time_label) {
        this.ahead_time_label = ahead_time_label;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", product_line='" + product_line + '\'' +
                ", order_qty=" + order_qty +
                ", start_time='" + start_time + '\'' +
                ", end_date='" + end_date + '\'' +
                ", product='" + product + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", notes='" + notes + '\'' +
                ", order_bom='" + order_bom + '\'' +
                ", postpone_label=" + postpone_label +
                ", ahead_time_label=" + ahead_time_label +
                '}';
    }
}
