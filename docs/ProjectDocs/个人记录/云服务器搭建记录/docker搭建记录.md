<!-- docker record -->

云服务器搭建docker

```shell
yum install -y yum-utils device-mapper-persistent-data lvm2
yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
yum list docker-ce --showduplicates | sort -r
yum -y install docker-ce-18.03.1.ce
systemctl start docker
systemctl enable docker     #Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.

```

docker-composed搭建记录

```shell
#有问题，目前使用的是从git上面直接下载上传到服务器上的操作
curl -L https://github.com/docker/compose/releases/download/2.24.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
mv docker-compose-Linux-x86_64 /usr/local/bin/
mv docker-compose-Linux-x86_64 docker-compose
chmod +x /usr/local/bin/docker-compose
docker-compose --version
```

mysql-docker搭建记录

```shell
docker search mysql

docker pull mysql:8.0.20

docker run  -p 3306:3306  --name mysql  \
-e MYSQL_ROOT_PASSWORD=mysqlroot  \
-v /app/docker/mysql/data/:/var/lib/mysql \
-v /app/docker/mysql/conf/:/etc/mysql/  \
-v /app/docker/mysql/logs:/var/log/mysql \
-v /app/docker/mysql/mysql-files:/var/lib/mysql-files \
-d  --user root  mysql:8.0.20
```

maxwell-docker搭建记录

```shell
#下载MaxWell镜像
docker pull zendesk/maxwell

#运行MaxWell
docker run --name maxwell  \
--restart=always  \
-d  zendesk/maxwell bin/maxwell  \
--user='数据库用户名' --password='数据库密码'  --host='IP地址' \
--producer=rabbitmq  \
--rabbitmq_user='MQ用户名' --rabbitmq_pass='MQ密码' --rabbitmq_host='IP地址' --rabbitmq_port='5672' \
--rabbitmq_exchange='maxwell_exchange'  --rabbitmq_exchange_type='fanout' \
--rabbitmq_exchange_durable='true' \
--filter='exclude: *.*, include: blog.tb_article.article_title = *, include: blog.tb_article.article_content = *, include: blog.tb_article.is_delete = *, include: blog.tb_article.status = *'
```

