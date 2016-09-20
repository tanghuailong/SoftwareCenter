# 软件园项目

### commit log 规范
---

* feat: 新功能
* fix: 修补bug
* docs: 文档
* style: 格式
* refactor: 重构
* test: 增加测试
* chore: 构建过程或辅助工具的变动

### 示例

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

