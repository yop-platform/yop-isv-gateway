# yop-isv-gateway

商户接收易宝商户通知的应用网关

## 项目结构

主要包路径的用途如下所示：

* com.yeepay.yop.isv.gateway.handler   事件处理器
* com.yeepay.yop.isv.gateway.servlet   接收事件的入口Servlet

## 使用说明

1.配置密钥信息，参考[配置文件说明](https://open.yeepay.com/docs/open/platform-doc/sdk_guide-sm/java-sdk-guide-sm#%E4%B8%83-%E9%99%84%E5%BD%95-%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E8%AF%A6%E7%BB%86%E8%AF%B4%E6%98%8E)

2.实现事件处理器 

可以参考 `com.yeepay.yop.isv.gateway.handler.MockCallbackHandler` 实现具体的事件处理逻辑。

3.启动网关

3.1 根目录下执行`mvn clean install -Dmaven.test.skip=true`编译打包

3.2 打开启动类`com.yeepay.yop.isv.gateway.WebApplication`启动网关

4.测试方法

4.1 运行本项目单元测试代码`com.yeepay.yop.isv.gateway.tests.YopCallbackTest`

4.2 商户通知方式的测试方法

在线文档地址：https://open.yeepay.com/docs/open/platform-doc/notifys/notify-summary-sm

本地模拟测试应用网关：

- 请求地址：[http://localhost:8080/yop-callback](http://localhost:8080/yop-callback)

- 请求方式：POST

- 请求类型：application/json

- 请求参数-header

    | 参数名           | 参数描述                               | 是否必传 |
    | ---------------- | ------------------------------------- | -------- |
    | Authorization     | 认证头(参考[鉴权认证机制](https://open.yeepay.com/docs/platform/sdk_guide/you_rsa)) | 是 |
    | x-yop-content-sm3 | 报文摘要                              | 是       |
    | x-yop-encrypt     | 加密头                                | 是       |
    | x-yop-appkey      | 应用key                               | 是       |
    | x-yop-sign-serial-no | 平台签名证书序列号                   | 是       |
    | x-yop-request-id  | 请求id（便于定位问题）                  | 是       |

- 请求参数-body

    ```json
    加密并做urlSafe的ase64编码的字符串
    ```

5.接入易宝-联调接口时

5.1 将网关应用部署、启动，正常运行后，确保公网可访问，可根据需要设置代理

5.2 在接口中预埋回调地址或登录 [开放平台](https://open.yeepay.com/) 设置商户通知地址：https://{公网ip}:{端口}/yop-callback

5.3 开始正常调试接口

当涉及商户回调时，可看到回调收到的参数信息和处理结果。
    
## 补充说明

1.本示例代码为同步处理，如业务量较大请转 MQ 异步削峰。
2.日志中出现关键字“you need to handle the new yop callback”，说明您有未对接的异步通知。
