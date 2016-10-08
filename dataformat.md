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



# 针对Android开放的API接口

## 规范说明

所有返回的json参数使用如下格式

```
{
    "code": 0, ===> 返回码，成功为0，失败为非0
    "message": "提示信息", ===> 提示信息
    "item": { ===> 字典
            "name": "test",
            "value": "2"
        },
    "items": [ ===> 列表
            {
                "name": "test",
                "value": "1"
            },
            {
                "name": "test",
                "value": "1"
            }
        ]
}
```

item和items里面的字典不得嵌套，如果出现需要嵌套的
情况，后端人员必须拆分接口，前端人员应拒绝使用复杂
程度过高的接口。

## 一、工位(一体机)请求数据组基本信息

### 1.1  功能描述

一体机展示数据前，需要明确知道数据组名称，折线名称，
已经相关单位信息。本接口用户获取数据组基本信息。

### 1.2 URL

```
/api/interface/opcua.groups/detail
```

### 1.3 请求方式

GET, POST

### 1.4 请求参数

station: 工位编号

### 1.5 返回参数

成功的数据

```
{
    "code": 0,
    "message": "success",
    "item": {},
    "items": [
            {
                "group": 1, ===> 数据组编号
                "name": "数据组名称",
                "x_name": "x轴单位名称",
                "x_percent": 20, ===> x轴比例
                "y_name": "y轴单位名称",
                "y_percent": 30, ===> y轴比例
            }
        ]
}
```

失败数据

```
{
    "code": 1,
    "message": "错误信息",
    "item": {},
    "items":[]
}
```

## 二、获取数据组折线基本信息

### 2.1 功能描述

Andriod获取数据组信息之后，根据数据组编号获取折线基本信息

### 2.2 URL

```
/api/interface/opcua.groups/line
```

### 2.3 请求方式

GET, POST

### 2.4 请求参数

group: 数据组编号

### 2.5 返回参数

成功数据
```
{
    "code": 0,
    "message": "success",
    "item": {}
    "items": [
            {
                "line": 1, ===> 折线编号
                "name": "折线名称",
                "unit": "单位",
                "color": "折线颜色",
                "topic": "需要订阅的主题名称"
            }
        ]
}
```

失败数据
```
{
    "code": 1,
    "message": "错误信息",
    "item": {},
    "items": []
}
```

## 三、数据文件下载功能

### 3.1 功能描述

设备数据展示完成之后，需要下载设备的数据

### 3.2 URL

```
/api/interface/opcua.lines.record/excel
```

### 3.3 请求方式

GET, POST

### 3.4 请求参数

group: 数据组编号

lines: 折线编号，多条折线编号用英文逗号进行分割

begin_time: 取MQTT第一次获取时间，不能加年月日，格式：10:08:53

end_time: 取MWTT最后一次获取时间，不能加年月日，格式：10:08:53

### 3.5 返回参数

成功数据返回为一个Excel文件

失败数据
```
{
    "code": 1,
    "message": "错误信息",
    "item": {}
    "items": []
}
```



# MQTT 推送数据格式

## 一、实时展示数据

### 1.1 主题

在请求api:
/api/interface/opcua.groups/line
返回的数据中，会有主题名称

### 1.2 推送格式

```
{
    "code": 0, 
    "message": "success",
    "items": []
    "item": {
            "line": 1, ===> 折线编号
            "value": "4", ===> 变化值
            "time": "09:45:40" ===> 时间
        },
}
```

### 1.3 推送说明

如果两次推送时间间隔小于等于3秒钟，不予推送。


