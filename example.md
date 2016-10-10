### 用法示例

* Retrofit RxJava 的写法


**compose和RxSubscriber**

```
            apiService.getLogoutResult(cardId).compose(RxSchedulerHelper.io_main()).subscribe(new RxSubscriber<LoginResult>() {
                @Override
                public void onNext(LoginResult loginResult) {
                    
                }
            });
```

* Mqtt 的注册Topic的写法

**TypeToken**

```
    TOPIC_ORDER("orders",new TypeToken<List<EventOrder>(){}.getType()),

```