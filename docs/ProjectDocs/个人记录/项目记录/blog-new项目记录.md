<!-- blog record 博客项目记录 -->

本项目是基于Blog项目变更的新版本。



blog-old跟blog-new区别：

|            blog-old            |                      blog-new                       |
| :----------------------------: | :-------------------------------------------------: |
|       spring版本为2.1.2        |                  spring版本为2.4.1                  |
| 部分网站展示设置为代码固定配置 |           部分网站展示部分设置为动态配置            |
| 首页搜索使用elasticsearch搜索  | 首页搜索可选，mysql数据库模糊搜索/elasticsearch搜索 |
|     文件上传模式为阿里oss      |      文件上传模式可选，本地服务器路径/oss上传       |
|    Nginx前后端使用相同端口     |               Nginx前后端使用独立端口               |
|               ——               |           优化log4j版本漏洞，升级为2.16.0           |
|               ——               |          优化FastJSON版本漏洞，升级为2.0.7          |
|               ——               |            前台页面新增看板娘/websocket             |
|    elasticsearch版本为6.6.0    |              elasticsearch版本为7.13.2              |



## vue 常用三个命令

~~~shell
## Project setup
```
npm install
```
### Compiles and hot-reloads for development
```
npm run serve
```
### Compiles and minifies for production
```
npm run build
```
~~~

# 1.本地试运行

使用工具：IntelliJ IDEA 2019.3.2 + node.V18.19.0

本地首次下拉项目：

（1）启动 springboot 项目；

（2）vue 项目需要到包含 “package.json”的目录下，

&ensp;&ensp;&ensp;&ensp;&ensp;开启 cmd 命令，

&ensp;&ensp;&ensp;&ensp;&ensp;更新配置：npm install     //第一次执行需要更新 node.js 关联包；

&ensp;&ensp;&ensp;&ensp;&ensp;启动：npm run serve

&ensp;&ensp;&ensp;&ensp;&ensp;本地未配置 port 端口，默认按照 8080 端口顺延。

> 运行异常一：npm ERR! request to https://registry.npm.taobao.org/vue failed, reason: certificate has expired

&ensp;&ensp;&ensp;&ensp;此 vue 之前为 2022 年之前创建，node 的 npm 淘宝镜像于 2022 年底进行了域名更换，https://registry.npm.taobao.org 更换为了 https://registry.npmmirror.com；

&ensp;&ensp;&ensp;&ensp;将代码 package-lock.json 文件中的内容全部替换。

> 运行异常二：Error: error: 0308010C: digital envelope routines:: unsupported

&ensp;&ensp;&ensp;&ensp;node 版本问题导致。本地安装 node 版本为 nodeJS.V17 以上，则项目本地运行需要关注启动配置（是否需要修改）。

```vue
##本地nodeJS.V17以下（不包含17）启动命令
## package.json
"scripts": {
  "serve": "vue-cli-service serve",
  "build": "vue-cli-service build",
  "lint": "vue-cli-service lint"
},
```

```vue
##本地nodeJS.V17以上启动命令
## package.json
"scripts": {
  "serve": "SET NODE_OPTIONS=--openssl-legacy-provider && vue-cli-service serve",
  "build": "SET NODE_OPTIONS=--openssl-legacy-provider && vue-cli-service build",
  "lint": "vue-cli-service lint"
},
```



# 2.服务器部署

使用工具：CentOS+JDK1.8+NGINX1.25.0+Node17.9.0+Elasticsearch7.13.2+Redis5.0.7+RabbitMQ3.12.12+MySQL8.0.20

**<span style="color:#FF0000;">注：本文章不介绍服务器上相关工具的安装，只介绍与项目有关的特殊配置部分。</span>**



（1）在本地对后端 spring，前端 vue 打包：

&ensp;&ensp;&ensp;&ensp;后端：maven clean

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;maven package

&ensp;&ensp;&ensp;&ensp;前端：后台 vue 和前台 vue

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;npm run build

（3）在服务器上设置相关路径后，上传前后端包；

（4）配置 NGINX 关联前后端：监听后端端口 port，关联前台页面路径和后台管理页面路径(俩个 dist)；

```nginx
    include       mime.types;
    default_type  application/octet-stream;

    sendfile        on;
    #tcp_nopush     on;

    #keepalive_timeout  0;
    keepalive_timeout  65;

    client_max_body_size     50m;
    client_body_buffer_size  10m; 
    client_header_timeout    1m;
    client_body_timeout      1m;

    #gzip  on;
    gzip on;
    gzip_min_length  1k;
    gzip_buffers     4 16k;
    gzip_comp_level  4;
    gzip_types text/plain application/javascript application/x-javascript text/css application/xml text/javascript application/x-httpd-php image/jpeg image/gif image/png;
    gzip_vary on;

    #前端页面
    server {
        listen      2334;
        server_name ${ip地址/域名};

        location / {
            root   ${前台dist绝对路径};   
            index  index.html index.htm;
            try_files $uri $uri/ /index.html;
        }

        location ^~ /api/ {  #配置前后端关联，以及跨域问题
            proxy_set_header Host $http_host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header REMOTE-HOST $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://${后端IP}:${后端port}/;
        }
        .....
    }


    #后端页面
    server {
        listen      2335
        server_name ${ip地址/域名};
     
        location / {		
            root   ${后台dist绝对路径};
            index  index.html index.htm; 
            try_files $uri $uri/ /index.html;	
        }
			
	location ^~ /api/ {		
            proxy_pass http://${后端IP}:${后端port}/;
	        proxy_set_header   Host             $host;
            proxy_set_header   X-Real-IP        $remote_addr;						
            proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;
        }
		
    }

    #websocket
    server {
        listen       2336;
        server_name  ${ip地址/域名};
     
        location / {
          proxy_pass http://${后端IP}:${后端port}/websocket;
          proxy_http_version 1.1;
          proxy_set_header Upgrade $http_upgrade;
          proxy_set_header Connection "Upgrade";
          proxy_set_header Host $host:$server_port;
          proxy_set_header X-Real-IP $remote_addr;
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          proxy_set_header X-Forwarded-Proto $scheme;
       }
	
    }

    #文件上传
    server {
        listen       2337;
        server_name  ${ip地址/域名};
     
        location / {		
          root ${本地存放上传文件的绝对路径}; 
        }		
		
    }

```

（5）可选：若搜索模式使用elasticsearch模式，需要额外配置：

&ensp;&ensp;&ensp;&ensp;使用postman发送json请求创建es索引：

```postman
http://xx.xx.xx.xx:9200/article/_doc
```

```json
{
    "mappings": {
        "properties": {
            "id": {
                "type": "integer"
            },
            "articleTitle": {
                "type": "text",
                "analyzer": "ik_max_word"
            },
            "articleContent": {
                "type": "text",
                "analyzer": "ik_max_word"
            },
            "isDelete": {
                "type": "integer"
            }
        }
    }
}
```

（6）使用 Java 命令运行：

```shell
java -jar -Xms512M -Xmx512M -XX:PermSize=256M /xxxxx/xxxxx.jar --spring.config.location=/xxxxx
/application.yml
```

> 运行异常一：blog_vue 前台页面显示空白。

&ensp;&ensp;&ensp;&ensp;因未申请SSL，将index.html中的https安全配置注释。

```html
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
<!--此页面下的所有资源按照https打开，将其注释-->
```

>  运行异常二：网站图片无法正常展示。

&ensp;&ensp;&ensp;&ensp;更改点 1：在页面上使用“开发者工具”——network刷新页面查看图片请求，请求路径存在异常。

&ensp;&ensp;&ensp;&ensp;在application.yml中将upload.local.配置需要完整配置http以及末尾的“/”。

```yaml
upload:
  local:
    # nginx映射本地文件路径，无域名则为 ip:83
    url: http://xx.xx.xx.xx:2337/
    # 本地文件存储路径
    path: /xxxx/xxx/
```

> 运行异常三：elasticsearch搜索只能上传，页面搜索无结果

&ensp;&ensp;&ensp;&ensp;更改点 1：检查elasticsearch的版本：

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;若spring版本在2.2.0以下，elasticsearch版本应低于7.0.0；

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;本项目spring版本为2.4.1，所以更改elasticsearch版本，更改为7.13.2。
