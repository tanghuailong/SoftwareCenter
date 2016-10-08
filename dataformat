HTTP接口
----------

 * 接口名称：登录

URL：/api/ic_login 

参数： ic_code, device（可选参数） 

访问方式: GET

返回值：


失败：

> {"msg": "Wrong login, please check your IC card!", "code": false}

 
成功：

> {"msg": "91e6bc24f8801a5b80f8d153a0f3c87e9cdc3111","code": true}

 * 接口名称：退出

URL：/api/logout

参数： ic_code

访问方式: GET

返回值：

成功：

> {"msg": "logout success", "code": true}

失败：

> {"msg": "Wrong logout, please check your IC card!", "code": false}

 * 接口名称：订单

URL：/api/orders

访问方式: GET

参数： 无

返回值：

> [
>     {
>         "status": "schedule",
>         "product": "产品01",
>         "end_date": "2016-11-30 14:26:21",
>         "order_bom": "云台V1",
>         "id": 1,
>         "product_line": false,
>         "name": "云台生产测试订单",
>         "order_qty": 10,
>         "postpone_label": false,
>         "priority": 9,
>         "ahead_time_label": false,
>         "start_time": "2016-09-07 14:26:18"
>     },
>     {
>         "status": "add",
>         "product": "产品01",
>         "end_date": "2016-09-21 16:39:15",
>         "order_bom": "云台V1",
>         "id": 2,
>         "product_line": false,
>         "name": "插单测试",
>         "order_qty": 1,
>         "postpone_label": false,
>         "priority": 1,
>         "ahead_time_label": false,
>         "start_time": "2016-09-06 16:39:12"
>     } ]

 * 接口名称：人员出勤信息

URL：/api/attendance

参数： 无

访问方式: GET

返回值：

>[
>    {
>        "hours": 0,
>        "login_datetime": "2016-09-28 16:54:39",
>        "login_device": "unknown",
>        "logout_datetime": "2016-09-28 16:54:43",
>        "staff": "Administrator"
>    },
>    {
>        "hours": 0,
>        "login_datetime": "2016-09-28 16:54:36",
>        "login_device": "unknown",
>        "logout_datetime": "2016-09-28 16:54:45",
>        "staff": "Administrator"
>    }
>]

备注：

第一条记录，当前信息。第二条记录，上次登录人员信息。

* 接口名称：APS排产结果

URL: /api/aps_result

Request:
>   None
Response:
>{
    "msg": "***",<br>
    "result": False or True,<br>
    "jobs":[{<br>
        'job_id': 作业任务编码
        'product_id': 产品编码
        'product_desc': 产品名称
        'job_content': 产品数量
        'job_start_time': 任务相对开始时间(s)
        'job_end_time': 任务相对结束时间(s)
        'deadline': 交货期
        'order_id': 订单ID
        'order_name': 订单名称
        'job_start_date': 实际开始时间
        'job_end_date': 实际结束时间
        'state': 当前状态
    },{...}],
    "processes":[{
        'process_id': 工序任务编码
        'start_time'：相对开始时间(s)
        'over_time': 相对结束时间(s)
        'job_id'：作业任务编码
        'operation_id'：工序编码
        'operation_dct_name'：工序名称
        'arranged_machine_station_id'：工位ID
        'arranged_machine_station_name'：工位名称
        'state'：当前状态
        'start_date'：实际开始时间
        'over_date'：实际结束时间
        'curr_step_id'：当前执行工步的编码
        'curr_step_dct_name'：当前执行工步的名称
    },{...}]
}

示例：
>{"msg": "SUCCESS", "processes": [{"over_date": false, "job_id": "0", "state": "draft", "arranged_machine_station_name": "机床1号加工工位", "start_time": 26800, "curr_step_id": false, "arranged_machine_station_id": "S004", "start_date": false, "over_time": 28800, "process_id": "1", "curr_step_dct_name": false, "operation_id": 6, "operation_dct_name": "机加工工序"}, {"over_date": false, "job_id": "0", "state": "draft", "arranged_machine_station_name": "组装工位", "start_time": 18976, "curr_step_id": false, "arranged_machine_station_id": "S003", "start_date": false, "over_time": 26800, "process_id": "0", "curr_step_dct_name": false, "operation_id": 5, "operation_dct_name": "组装工序"}, {"over_date": false, "job_id": "1", "state": "producing", "arranged_machine_station_name": "机床2号加工工位", "start_time": 4376, "curr_step_id": false, "arranged_machine_station_id": "S001", "start_date": "2016-09-27 18:23:24", "over_time": 7602, "process_id": "5", "curr_step_dct_name": false, "operation_id": 4, "operation_dct_name": "机加工工序"}, {"over_date": false, "job_id": "1", "state": "producing", "arranged_machine_station_name": "检测工位", "start_time": 4376, "curr_step_id": false, "arranged_machine_station_id": "S002", "start_date": "2016-09-27 18:23:24", "over_time": 8022, "process_id": "6", "curr_step_dct_name": false, "operation_id": 5, "operation_dct_name": "检测工序"}], "jobs": [{"job_start_time": 4376, "job_id": "1", "product_desc": "云台设备", "order_id": 2, "job_end_time": 54900, "job_end_date": false, "job_content": 7, "order_name": "2", "state": "draft", "deadline": 54900, "job_start_date": false, "product_id": "P002"}, {"job_start_time": 18976, "job_id": "0", "product_desc": "云台顶盖", "order_id": 1, "job_end_time": 39900, "job_end_date": false, "job_content": 5, "order_name": "1", "state": "draft", "deadline": 39900, "job_start_date": false, "product_id": "P001"}], "result": "True"}


MTQQ接口
------

 * 接口名称：订单

Topic：ciiic/orders

参数： 无

返回值：

> [
>     {
>         "status": "schedule",
>         "product": "产品01",
>         "end_date": "2016-11-30 14:26:21",
>         "order_bom": "云台V1",
>         "id": 1,
>         "product_line": false,
>         "name": "云台生产测试订单",
>         "order_qty": 10,
>         "postpone_label": false,
>         "priority": 9,
>         "ahead_time_label": false,
>         "start_time": "2016-09-07 14:26:18"
>     },
>     {
>         "status": "add",
>         "product": "产品01",
>         "end_date": "2016-09-21 16:39:15",
>         "order_bom": "云台V1",
>         "id": 2,
>         "product_line": false,
>         "name": "插单测试",
>         "order_qty": 1,
>         "postpone_label": false,
>         "priority": 1,
>         "ahead_time_label": false,
>         "start_time": "2016-09-06 16:39:12"
>     } ]


 * 接口名称：报警

Topic：ciiic/alarm

参数： 无

返回值：

> [
	{
>         device: "测试数据002", 
>         date: "2016-09-02", 
>         message: "懂的法国", 
>         state: "solved",
>         station:  "组装工位"
> 	},
>	{
>       "device": "测试数据005",
>       "date": "2016-09-22",
>       "message": "瑞士军刀男",
>       "state": "unsolved",
>         station:  "检测工位"
>       "device_code": "mj_test_005"
>   },
>	...
> ]
