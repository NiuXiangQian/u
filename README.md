## u - 短网址平台
短短短！短得不能再短。缩短你的超链接<br>
以打造一个高性能，可快速部署的短网址服务平台

## 系统说明：
- 基于 Spring Boot Redis实现的一个短网址平台
### 目前
- 支持web方式和api方式创建短网址
### 后续
- 将支持web控制台进行动态配置
- 支持更丰富的api操作
- ...
### Web方式效果图
![效果图1/doc/demo_1.png](/doc/demo_1.png)
![效果图2/doc/demo_2.png](/doc/demo_2.png)
  待完善...
  
### API方式使用
```
post http://127.0.0.1:8080/api/v1/generate

body {"ak":"ak_123",
      "sk":"sk_123",
       "url":"https://www.baidu.com/s?wd=a&rsv_spt=1&rsv_iqid=0xd1a6a2e5000e2bc1&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=1&rsv_sug2=0&rsv_btype=i&inputT=509&rsv_sug4=509"}

respone {"code": 1,
        	"success": true,
        	"msg": "ok",
        	"data": "http://127.0.0.1:8080/u/ff185ccd0439f762"
        }
 
```
ak 和 sk 为访问api密钥，上例使用的是在系统类com.github.niu.u.init.BootInit内置的默认的
  
### 核心依赖
  
  | 依赖                   | 版本          |
  | ---------------------- | ------------- |
  | Spring Boot            | 2.3.6.RELEASE |
  | Spring Boot Redis      | 2.3.6.RELEASE  |
  | layui                  | 2.5.7        |

###  环境需要
- Java 1.8.0_211
- Maven 3.6.3
- Redis  3.x

### application.yml修改
```bash
spring:
  redis:
    database: 1
    host: 127.0.0.1
    port: 6379
#    password:
short_url:
  server: http://127.0.0.1:8080  #这里是短网址服务器地址
```


### 其他说明


1. 欢迎提交 [issue](https://github.com/1603565290m/u/issues)，请写清楚遇到问题的原因、开发环境、复显步骤。

2. 联系作者QQ：1603565290

