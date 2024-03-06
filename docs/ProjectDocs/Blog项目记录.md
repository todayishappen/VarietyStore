## vue 常用三个命令：

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

​          开启 cmd 命令，

​          更新配置：npm install     //第一次执行需要更新 node.js 关联包

​          启动：npm run serve

​          本地未配置 port 端口，默认按照 8080 端口顺延。

①运行异常一：

npm ERR! request to https://registry.npm.taobao.org/vue failed, reason: certificate has expired

​    此 vue 之前为 2022 年之前创建，node 的 npm 淘宝镜像于 2022 年底进行了域名更换，https://registry.npm.taobao.org 更换为了 https://registry.npmmirror.com；

​    将代码 package-lock.json 文件中的内容全部替换。

②运行异常二：

Error: error: 0308010C: digital envelope routines:: unsupported

​    node 版本问题导致。本地安装 node 版本为 nodeJS.V17 以上，则项目本地运行需要关注启动配置（是否需要修改）

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



# 2.服务器部署

使用工具：CentOS+JDK1.8+NGINX+node17.9.0

（1）在本地对后端 spring，前端 vue 打包：

​	后端：maven clean

​		   maven package

​	前端：后台 vue 和前台 vue

​		    npm run build

（3）在服务器上设置相关路径后，上传前后端包；

（4）配置 NGINX 关联前后端：监听后端端口 port，关联前台页面路径和后台管理页面路径(俩个 dist)；



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

（5）使用 Java 命令运行：

```shell
java -jar -Xms512M -Xmx512M -XX:PermSize=256M /xxxxx/xxxxx.jar --spring.config.location=/xxxxx
/application.yml
```

①运行异常一：

blog_vue 前台页面显示空白。

​       更改点 1：在页面上使用“开发者工具”——console 存在报错 "net:: ERR_SSL_PROTOCOL_ERROR"

​       vue.config.js 文件中，检查 "publicPath" 参数，需要保持和 NGINX 配置一致。

​       此 vue 配置表示 build 打包后的 index 内容中包含的 js，css 等文件路径为指定 publicPath 路径。否则页面会找不到文件，显示空白。

```nginx
location / {
```

```vue
module.exports = {
       publicPath: "./",
       .....
```

​      更改点 2：在页面上使用“开发者工具”——console 存在报错 "404 not found"【前台页面无此报错】

​       index.js 文件中，检查 vue 路由方式，vue 路由，注释 history 选择默认 base，或者配置 base，否则页面无法连接后端导致查询不到数据显示空白。此处 blog_vue 前台页面自行配置了 base 方式。

```vue
const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});
```

​      更改点 3：在页面上使用“开发者工具”——Network 重新抓取当前页面数据，发现 index.html 为 "http://"，而 js，css 文件的 Request URL 请求均为 "https://"（本人申请的云服务器还未购买 SSL 证书，只有 http）。这种内外不一致的原因是 index.html 文件的一个配置导致的。

​      将index.html中的https安全配置注释。

```html
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
<!--此页面下的所有资源按照https打开，将其注释-->
```

 ②运行异常二：

admin_vue 后台管理页面显示空白。

​     更改点 1：在页面上使用“开发者工具”——console 存在报错 "net:: ERR_SSL_PROTOCOL_ERROR"

​     vue.config.js 文件中，检查 "publicPath" 参数，需要保持和 NGINX 配置一致。

```nginx
location ^~/admin {
```

```vue
module.exports = {
  publicPath: "/admin/",
  ......
```

​     更改点 2：在页面上使用“开发者工具”——console 存在报错“404 not found”

​     index.js 文件中，检查 vue 路由方式，vue 路由，注释 history 选择默认 base，或者配置 base，否则页面无法连接后端导致查询不到数据显示空白。

```vue
const router = new VueRouter({
  // mode: "history",    //vue 路由，注释history选择默认base，或者配置base，否则页面空白
  routes
});
```
