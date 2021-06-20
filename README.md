# yop-isv-event-gateway

商户接收易宝商户通知的应用网关

## 项目结构

### 1. yop-isv-event-sdk 接收事件的 SDK

主要包路径的用途如下所示：

* com.yeepay.yop.isv.event.sdk.cert      加载证书相关的
* com.yeepay.yop.isv.event.sdk.constant  常量
* com.yeepay.yop.isv.event.sdk.dto       DTO
* com.yeepay.yop.isv.event.sdk.enums     枚举
* com.yeepay.yop.isv.event.sdk.exception 异常
* com.yeepay.yop.isv.event.sdk.handler   事件处理器
* com.yeepay.yop.isv.event.sdk.resolver  请求报文解析器
* com.yeepay.yop.isv.event.sdk.support   工具类

### 2. yop-isv-event-gateway 接收事件的应用网关

主要包路径的用途如下所示：

* com.yeepay.yop.isv.event.gateway.cert    加载证书相关的实现
* com.yeepay.yop.isv.event.gateway.config  配置
* com.yeepay.yop.isv.event.gateway.handler 事件处理器的实现
* com.yeepay.yop.isv.event.gateway.servlet 接收事件的入口Servlet

## 使用说明

1.明确证书加载方式

为方便商户自行扩展，本项目提供了两种证书定位方式：

* 采用默认的证书定位器 ConfigFileCertLocator。默认开启，或将配置文件 application.yml 中的 yop.certs.locator 设置为 file 开启。
* 修改证书定位器的组合 CompositeCertLocator，从任意的位置读取商户的密钥。将配置文件 application.yml 中的 yop.certs.locator 设置为 composite 开启。

2.实现事件处理器 

可以参考 com.yeepay.yop.isv.event.sdk.handler.impl.YopWildCardEventHandler 实现具体的事件处理逻辑。

3.启动网关

3.1 根目录下执行`mvn clean install -Dmaven.test.skip=true`编译打包
3.2 模块yop-isv-event-gateway目录下执行`mvn spring-boot:run`启动网关

4.测试方法

4.1 运行本项目单元测试代码

4.2 商户通知方式的测试方法

报文为数字信封，解析代码参见 com.yeepay.yop.isv.event.sdk.resolver.impl.NotifyRequestResolver
在线文档地址：https://open.yeepay.com/docs/v2/platform/notify-summary/notify-process/index.html

4.3 事件通知方式的测试方法

报文为明文，解析代码参见 com.yeepay.yop.isv.event.sdk.resolver.impl.NotifyRequestResolver

本地模拟测试应用网关：

    - 请求地址：[http://localhost:8081/yop-event]()

    - 请求方式：POST

    - 请求类型：application/json

    - 请求参数-header

        | 参数名           | 参数描述                               | 是否必传 |
        | ---------------- | -------------------------------------- | -------- |
        | x-yop-sign       | 签名(RSA)                                   | 是       |
        | x-yop-sign-type  | 签名算法（目前支持SHA256/SHA512）                               | 是       |
        | x-yop-appkey     | 应用key                                | 是       |
        | x-yop-event-type | 事件类型，针对不同事件，调用不同处理器 | 是       |
        | x-yop-request-id | 请求id（便于定位问题）                 | 是       |

    - 请求参数-body

        ```json
        {}
        ```

5.接入易宝-联调接口时

5.1 将网关应用部署、启动，正常运行后，确保公网可访问，可根据需要设置代理

5.2 在接口中预埋回调地址或登录 [开放平台](https://open.yeepay.com/) 设置商户通知地址：https://{公网ip}:{端口}/yop-event

5.3 开始正常调试接口

当涉及商户回调时，可看到回调收到的参数信息和处理结果。
    
## 补充说明

1.本示例代码为同步处理，如业务量较大请转 MQ 异步削峰。
2.日志中出现关键字“you need to handle the new yop event”，说明您有未对接的异步通知。
