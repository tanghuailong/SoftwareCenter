##项目说明

### 项目使用技术
* 使用RxJava和Retrofit 来实现访问服务器的接口
* 使用 mqtt 来实现实时数据推送
* 使用 butterknife 注解
* 使用 EventBus 进行消息的传递
* 使用 lombok 注解，减少冗余代码

### 技术点
* 推送 Android方面通过mqtt协议通信，实现实时获取服务器的数据
* 自己拉取  retrofit访问接口。
* 异步处理 RxJava

### commit log 规范


* feat: 新功能
* fix: 修补bug
* docs: 文档
* style: 格式
* refactor: 重构
* test: 增加测试
* chore: 构建过程或辅助工具的变动

#### 示例

```
feat($browser): onUrlChange event (popstate/hashchange/polling)

Added new event to $browser:
- forward popstate event if available
- forward hashchange event if popstate not available
- do polling when neither popstate nor hashchange available

Breaks $browser.onHashChange, which was removed (use onUrlChange instead)

```


### 参考链接

[git commit log 规范](http://www.ruanyifeng.com/blog/2016/01/commit_message_change_log.html)

