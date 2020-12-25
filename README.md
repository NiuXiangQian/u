![Github issues](https://img.shields.io/github/issues/1603565290m/u)
![Github stars](https://img.shields.io/github/stars/1603565290m/u)
![GitHub forks](https://img.shields.io/github/forks/1603565290m/u)
![GitHub license](https://img.shields.io/github/license/1603565290m/u)

## u - 短网址平台
短短短！短得不能再短。缩短你的超链接<br>
以打造一个高性能，可快速部署的短网址服务平台<br>
gitee地址：[https://gitee.com/m1603565290/url](https://gitee.com/m1603565290/url) <br>
github地址：[https://github.com/NiuXiangQian/u](https://github.com/NiuXiangQian/u)

### 我能干什么
短链接我们或多或少都使用过，所谓短链接就是根据较长的原链接url生成一段较短的链接，访问短链接可以跳转到对应的原链接，这样做好处在于：1. url更加美观；2. 便于保存和传播；3. 某些网站内容发布有字数限制，短链接可以节约字数。<br>
我只为缩短你的超链接url，让你变得短短的，不。。。是你的url变得短短的。
## 系统说明：
- 基于 Spring Boot Redis实现的一个短网址平台
### 目前
- 支持web方式和api方式创建短网址
- 支持api解析短网址
### 后续
- 将支持web控制台进行动态配置进行管控
- 增加监控功能
- 支持更丰富的api操作
- ...
### Web方式效果图
![效果图1](https://niu-github.oss-cn-beijing.aliyuncs.com/u/demo_1.png)
![效果图2](https://niu-github.oss-cn-beijing.aliyuncs.com/u/demo_2.png)
 ![操作](
https://niu-github.oss-cn-beijing.aliyuncs.com/u/opt.gif)
  
### API方式使用
 **`Access Key Id（AK）用于标示用户，Secret Access Key（SK）是用户用于加密认证字符串和云厂商用来验证认证字符串的密钥`**<br>
- 首先需要ak和sk的授权才可以访问api接口，当然你可以在你系统中取消此项认证<br>
在com.github.niu.u.config.WebConfig中中注释掉如下代码即可取消认证<br>
```
//添加api权限拦截器拦截所有api
registry.addInterceptor(apiAccessInterceptor).addPathPatterns("/api/v1/**");
```
默认的ak和sk在com.github.niu.u.init.BootInit类中有体现设置，此功能将会在后期版本完善，尽请期待
- 假如使用了ak、sk授权认证，你需要在请求头或者请求参数加上如下值
```
"ak" :"你的ak"
"sk" :"你的sk"
```
- 接口使用样例 (生成短网址)
```
post http://127.0.0.1:8080/api/v1/generate?ak=ak_123&sk=sk_123

type application/json

body {
    "url":"https://www.baidu.com/s?wd=a&rsv_spt=1&rsv_iqid=0xd1a6a2e5000e2bc1&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=1&rsv_sug2=0&rsv_btype=i&inputT=509&rsv_sug4=509",
    "valid":14400
 }
参数说明：url 传入的长网址
        valid 有效时间（秒），默认四个小时，可以不用穿，-1永久有效

respone {"code": 1,
        "success": true,
        "msg": "ok",
        "data": {
        	"orgUrl": "https://www.baidu.com/s?wd=a&rsv_spt=1&rsv_iqid=0xd1a6a2e5000e2bc1&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_dl=tb&rsv_sug3=1&rsv_sug2=0&rsv_btype=i&inputT=509&rsv_sug4=509",
        	"shortUrl": "http://127.0.0.1:8080/u/ff185ccd0439f762",
        	"shortTarget": "ff185ccd0439f762",
        	"validTime": 14400
        	}
        }
```

  
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


## 其他说明


1. 欢迎提交 [issue](https://github.com/1603565290m/u/issues)，请写清楚遇到问题的原因、开发环境、复显步骤。
2. 有更多的功能想法欢迎提出
3. 联系作者QQ：1603565290
4. 博客地址：https://laoniu.blog.csdn.net/article/details/111696071

