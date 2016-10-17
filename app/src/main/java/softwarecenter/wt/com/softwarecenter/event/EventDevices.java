package softwarecenter.wt.com.softwarecenter.event;

import java.util.List;

/**
 * 设备信息实体类
 * Created by chenggong on 2016/10/13.
 */


public class EventDevices {

    /**
     * msg : SUCCESS
     * devices_info : [{"processes":[{"over_date":"","job_id":"1","state":"draft","arranged_machine_station_name":"机床1号加工工位","start_time":"0","arranged_machine_station_id":"S001","start_date":"2016-10-13 09:31:59","over_time":"7602","process_id":"5","operation_id":"4","operation_dct_name":"机加工工序"}],"code":"Ciiic_d001","name":"加工工位1号设备","speed":0,"processing":"","state":"unused","curr_order_code":"","type":"ordinary","curr_order_name":""},{"processes":[{"over_date":"","job_id":"1","state":"draft","arranged_machine_station_name":"机床2号加工工位","start_time":"4376","arranged_machine_station_id":"S002","start_date":"","over_time":"8022","process_id":"6","operation_id":"5","operation_dct_name":"组装工序"}],"code":"Ciiic_d002","name":"加工工位2号设备","speed":0,"processing":"","state":"unused","curr_order_code":"","type":"ordinary","curr_order_name":""},{"processes":[{"over_date":"","job_id":"0","state":"draft","arranged_machine_station_name":"组装工位","start_time":"18976","arranged_machine_station_id":"S003","start_date":"","over_time":"26800","process_id":"0","operation_id":"5","operation_dct_name":"组装工序"}],"code":"Ciiic_d003","name":"组装设备","speed":0,"processing":"","state":"unused","curr_order_code":"","type":"ordinary","curr_order_name":""},{"processes":[{"over_date":"","job_id":"0","state":"draft","arranged_machine_station_name":"检测工位","start_time":"26800","arranged_machine_station_id":"S004","start_date":"","over_time":"28800","process_id":"1","operation_id":"6","operation_dct_name":"检测工序"}],"code":"Ciiic_d004","name":"检测设备","speed":0,"processing":"","state":"unused","curr_order_code":"","type":"ordinary","curr_order_name":""}]
     * result : True
     */

    private String msg;
    private String result;
    /**
     * processes : [{"over_date":"","job_id":"1","state":"draft","arranged_machine_station_name":"机床1号加工工位","start_time":"0","arranged_machine_station_id":"S001","start_date":"2016-10-13 09:31:59","over_time":"7602","process_id":"5","operation_id":"4","operation_dct_name":"机加工工序"}]
     * code : Ciiic_d001
     * name : 加工工位1号设备
     * speed : 0.0
     * processing :
     * state : unused
     * curr_order_code :
     * type : ordinary
     * curr_order_name :
     */

    private List<DevicesInfoBean> devices_info;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<DevicesInfoBean> getDevices_info() {
        return devices_info;
    }

    public void setDevices_info(List<DevicesInfoBean> devices_info) {
        this.devices_info = devices_info;
    }

    public static class DevicesInfoBean {
        private String code;
        private String name;
        private double speed;
        private String processing;
        private String state;
        private String curr_order_code;
        private String type;
        private String curr_order_name;
        /**
         * over_date :
         * job_id : 1
         * state : draft
         * arranged_machine_station_name : 机床1号加工工位
         * start_time : 0
         * arranged_machine_station_id : S001
         * start_date : 2016-10-13 09:31:59
         * over_time : 7602
         * process_id : 5
         * operation_id : 4
         * operation_dct_name : 机加工工序
         */

        private List<ProcessesBean> processes;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public String getProcessing() {
            return processing;
        }

        public void setProcessing(String processing) {
            this.processing = processing;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCurr_order_code() {
            return curr_order_code;
        }

        public void setCurr_order_code(String curr_order_code) {
            this.curr_order_code = curr_order_code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCurr_order_name() {
            return curr_order_name;
        }

        public void setCurr_order_name(String curr_order_name) {
            this.curr_order_name = curr_order_name;
        }

        public List<ProcessesBean> getProcesses() {
            return processes;
        }

        public void setProcesses(List<ProcessesBean> processes) {
            this.processes = processes;
        }

        public static class ProcessesBean {
            private String over_date;
            private String job_id;
            private String state;
            private String arranged_machine_station_name;
            private String start_time;
            private String arranged_machine_station_id;
            private String start_date;
            private String over_time;
            private String process_id;
            private String operation_id;
            private String operation_dct_name;

            public String getOver_date() {
                return over_date;
            }

            public void setOver_date(String over_date) {
                this.over_date = over_date;
            }

            public String getJob_id() {
                return job_id;
            }

            public void setJob_id(String job_id) {
                this.job_id = job_id;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getArranged_machine_station_name() {
                return arranged_machine_station_name;
            }

            public void setArranged_machine_station_name(String arranged_machine_station_name) {
                this.arranged_machine_station_name = arranged_machine_station_name;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getArranged_machine_station_id() {
                return arranged_machine_station_id;
            }

            public void setArranged_machine_station_id(String arranged_machine_station_id) {
                this.arranged_machine_station_id = arranged_machine_station_id;
            }

            public String getStart_date() {
                return start_date;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getOver_time() {
                return over_time;
            }

            public void setOver_time(String over_time) {
                this.over_time = over_time;
            }

            public String getProcess_id() {
                return process_id;
            }

            public void setProcess_id(String process_id) {
                this.process_id = process_id;
            }

            public String getOperation_id() {
                return operation_id;
            }

            public void setOperation_id(String operation_id) {
                this.operation_id = operation_id;
            }

            public String getOperation_dct_name() {
                return operation_dct_name;
            }

            public void setOperation_dct_name(String operation_dct_name) {
                this.operation_dct_name = operation_dct_name;
            }
        }
    }
}
