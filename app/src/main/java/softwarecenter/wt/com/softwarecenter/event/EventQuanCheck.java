package softwarecenter.wt.com.softwarecenter.event;

import java.util.List;

/**
 * Created by chenggong on 2016/10/15.
 */

public class EventQuanCheck {

    /**
     * record_info : [{"category_code":"categ-01","category_name":"外观","product_id":"test7","product_desc":"test7","through_post_time":"2016-10-15 05:50:39","quality_item_name":"顶盖颜色","quality_post_name":"检测工位","quality_post_id":"S004","quality_item_code":"item_002","quality_result":"ng"}]
     * item_category : [{"category_code":"categ-01","include_items":[{"item_name":"刮痕","inspection_content":"外观顶盖是否有刮痕","item_code":"item_001"},{"item_name":"顶盖颜色","inspection_content":"顶盖喷涂颜色是否正常，银白色","item_code":"item_002"},{"item_name":"顶盖颜色","inspection_content":"顶盖喷涂颜色是否正常，银白色","item_code":"item_002"}],"category_name":"外观"},{"category_code":"categ_02","include_items":[{"item_name":"螺丝个数","inspection_content":"螺丝个数是否正确","item_code":"item_003"},{"item_name":"螺丝个数","inspection_content":"螺丝个数是否正确","item_code":"item_003"}],"category_name":"螺丝"},{"category_code":"categ_03","include_items":[{"item_name":"运转状况","inspection_content":"是否运行良好","item_code":"item_004"},{"item_name":"电路","inspection_content":"电路是否通电正常","item_code":"item_005"}],"category_name":"QC"}]
     * ng_detail : [{"category_code":"categ-01","ratio":0.3333,"category_name":"外观"},{"category_code":"categ_02","ratio":0.2222,"category_name":"螺丝"},{"category_code":"categ_03","ratio":0.2223,"category_name":"QC"}]
     * ok_ratio : 0.2222
     * result : True
     * msg : SUCCESS
     */

    private double ok_ratio;
    private String result;
    private String msg;
    /**
     * category_code : categ-01
     * category_name : 外观
     * product_id : test7
     * product_desc : test7
     * through_post_time : 2016-10-15 05:50:39
     * quality_item_name : 顶盖颜色
     * quality_post_name : 检测工位
     * quality_post_id : S004
     * quality_item_code : item_002
     * quality_result : ng
     */

    private List<RecordInfoBean> record_info;
    /**
     * category_code : categ-01
     * include_items : [{"item_name":"刮痕","inspection_content":"外观顶盖是否有刮痕","item_code":"item_001"},{"item_name":"顶盖颜色","inspection_content":"顶盖喷涂颜色是否正常，银白色","item_code":"item_002"},{"item_name":"顶盖颜色","inspection_content":"顶盖喷涂颜色是否正常，银白色","item_code":"item_002"}]
     * category_name : 外观
     */

    private List<ItemCategoryBean> item_category;
    /**
     * category_code : categ-01
     * ratio : 0.3333
     * category_name : 外观
     */

    private List<NgDetailBean> ng_detail;

    public double getOk_ratio() {
        return ok_ratio;
    }

    public void setOk_ratio(double ok_ratio) {
        this.ok_ratio = ok_ratio;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<RecordInfoBean> getRecord_info() {
        return record_info;
    }

    public void setRecord_info(List<RecordInfoBean> record_info) {
        this.record_info = record_info;
    }

    public List<ItemCategoryBean> getItem_category() {
        return item_category;
    }

    public void setItem_category(List<ItemCategoryBean> item_category) {
        this.item_category = item_category;
    }

    public List<NgDetailBean> getNg_detail() {
        return ng_detail;
    }

    public void setNg_detail(List<NgDetailBean> ng_detail) {
        this.ng_detail = ng_detail;
    }

    public static class RecordInfoBean {
        private String category_code;
        private String category_name;
        private String product_id;
        private String product_desc;
        private String through_post_time;
        private String quality_item_name;
        private String quality_post_name;
        private String quality_post_id;
        private String quality_item_code;
        private String quality_result;

        public String getCategory_code() {
            return category_code;
        }

        public void setCategory_code(String category_code) {
            this.category_code = category_code;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_desc() {
            return product_desc;
        }

        public void setProduct_desc(String product_desc) {
            this.product_desc = product_desc;
        }

        public String getThrough_post_time() {
            return through_post_time;
        }

        public void setThrough_post_time(String through_post_time) {
            this.through_post_time = through_post_time;
        }

        public String getQuality_item_name() {
            return quality_item_name;
        }

        public void setQuality_item_name(String quality_item_name) {
            this.quality_item_name = quality_item_name;
        }

        public String getQuality_post_name() {
            return quality_post_name;
        }

        public void setQuality_post_name(String quality_post_name) {
            this.quality_post_name = quality_post_name;
        }

        public String getQuality_post_id() {
            return quality_post_id;
        }

        public void setQuality_post_id(String quality_post_id) {
            this.quality_post_id = quality_post_id;
        }

        public String getQuality_item_code() {
            return quality_item_code;
        }

        public void setQuality_item_code(String quality_item_code) {
            this.quality_item_code = quality_item_code;
        }

        public String getQuality_result() {
            return quality_result;
        }

        public void setQuality_result(String quality_result) {
            this.quality_result = quality_result;
        }
    }

    public static class ItemCategoryBean {
        private String category_code;
        private String category_name;
        /**
         * item_name : 刮痕
         * inspection_content : 外观顶盖是否有刮痕
         * item_code : item_001
         */

        private List<IncludeItemsBean> include_items;

        public String getCategory_code() {
            return category_code;
        }

        public void setCategory_code(String category_code) {
            this.category_code = category_code;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public List<IncludeItemsBean> getInclude_items() {
            return include_items;
        }

        public void setInclude_items(List<IncludeItemsBean> include_items) {
            this.include_items = include_items;
        }

        public static class IncludeItemsBean {
            private String item_name;
            private String inspection_content;
            private String item_code;

            public String getItem_name() {
                return item_name;
            }

            public void setItem_name(String item_name) {
                this.item_name = item_name;
            }

            public String getInspection_content() {
                return inspection_content;
            }

            public void setInspection_content(String inspection_content) {
                this.inspection_content = inspection_content;
            }

            public String getItem_code() {
                return item_code;
            }

            public void setItem_code(String item_code) {
                this.item_code = item_code;
            }
        }
    }

    public static class NgDetailBean {
        private String category_code;
        private double ratio;
        private String category_name;

        public String getCategory_code() {
            return category_code;
        }

        public void setCategory_code(String category_code) {
            this.category_code = category_code;
        }

        public double getRatio() {
            return ratio;
        }

        public void setRatio(double ratio) {
            this.ratio = ratio;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
