vue常用三个命令：

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



1.本地试运行

使用工具：IntelliJ IDEA 2019.3.2 + node.V18.19.0

本地首次下拉项目：

（1）启动springboot项目；

（2）vue项目需要到包含 “package.json”的目录下，

​          开启cmd命令，

​          更新配置：npm install     //第一次执行需要更新node.js关联包

​          启动：npm run serve

​          本地未配置port端口，默认按照8080端口顺延。

①运行异常一：

npm ERR! request to https://registry.npm.taobao.org/vue failed, reason: certificate has expired

​    此vue之前为2022年之前创建，node的npm淘宝镜像于2022年底进行了域名更换，https://registry.npm.taobao.org 更换为了 https://registry.npmmirror.com；

​    将代码package-lock.json文件中的内容全部替换。

②运行异常二：

Error: error:0308010C:digital envelope routines::unsupported

​    node版本问题导致。本地安装node版本为nodeJS.V17以上，则项目本地运行需要关注启动配置（是否需要修改）

```vue
##本地nodeJS.V17以下（不包含17）启动命令
## package.json
"scripts": {
  "serve": "SET NODE_OPTIONS=--openssl-legacy-provider && vue-cli-service serve",
  "build": "SET NODE_OPTIONS=--openssl-legacy-provider && vue-cli-service build",
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



2.服务器部署

使用工具：CentOS

在本地对后端spring，前端vue打包：

（1）后端：maven clean

​                      maven package

（2）前端：后台vue和前台vue

​                      npm run build

（3）在服务器上设置相关路径后，上传前后端包；

（4）配置NGINX关联前后端：监听后端端口port，关联前端页面路径；



```nginx

   server {
        listen      ${设置nginx代理端口号};
        server_name  ${项目名};

        #charset koi8-r;

        #access_log  logs/host.access.log  main;

        location / {
            root   ${前台dist绝对路径};   
            index  index.html index.htm;
        }

        location ^~/${后台管理路径} {
            alias   ${后台管理dist绝对路径};
            try_files $uri $uri/ /admin/index.html;
        }


        location /api/ {  #配置前后端关联，以及跨域问题
            proxy_set_header Host $http_host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header REMOTE-HOST $remote_addr;
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_pass http://${后端IP}:${后端port}/;
        }
        .....
    }
```



（5）使用Java命令运行：

```shell
java -jar -Xms512M -Xmx512M -XX:PermSize=256M /xxxxx/xxxxx.jar --spring.config.location=/xxxxx
/application.yml
```

