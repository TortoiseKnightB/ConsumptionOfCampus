# ConsumptionOfCampus

对数据进行一些可视化的展示，个人练手项目

### 用到的技术与服务

- JavaWeb相关：html、css、tomcat、**servlet**、jsp、EL表达式、JSTL标签库
- 服务器相关：nginx、阿里云轻量应用服务器（CentOS 8.2）
- 数据库相关：mysql、JDBC、druid数据库连接池、dbutils

### 关键步骤

##### 安装数据库

- 下载MySQL：`yum -y install mysql mysql-server.x86_64 mysql-devel.x86_64`
- 登入MySQL：` mysql -u root`
- 修改密码：`alter user 'root'@'localhost' identified by 'newPassword';`
- 创建数据库` create database campus_consumption;`
- 导入数据文件 source.sql：`source /data/source.sql`

##### 安装JDK


- `yum -y install java-1.8.0-openjdk`
- `yum -y install java-1.8.0-openjdk-devel.x86_64 `

##### 安装tomcat

- `cd /usr/src/`
- 解压 tomcat：` tar -xvf apache-tomcat-8.5.66.tar`
- `cd /usr/src/apache-tomcat-8.5.66/bin`
- 启动服务：`./startup.sh `

##### 开放3306、8080端口

- 此处可能需要手动去阿里云服务器控制台添加防火墙端口
- 添加开放端口：`firewall-cmd --zone=public --add-port=3306/tcp --permanent`
- `firewall-cmd --zone=public --add-port=8080/tcp --permanent`
- 重启防火墙：`systemctl restart firewalld`
- `firewall-cmd --zone=public --list-ports`

##### 安装nginx

- `cd /usr/src/`
- 下载依赖文件：`yum -y install gcc pcre-devel openssl openssl-devel zlib zlib-devel `
- 解压 nginx：` tar -xvf nginx-1.20.1.tar `
- `cd nginx-1.20.1/`
- 配置：`./configure`
- 编译&&安装：` make && make install `
- 开放防火墙端口：`firewall-cmd --zone=public --add-port=80/tcp --permanent`
- 重启防火墙：`systemctl restart firewalld`
- `cd /usr/local/nginx/sbin`
- 启动nginx服务：`./nginx `

##### 项目部署到tomcat

- `cd /usr/src/apache-tomcat-8.5.66/webapps`
- 如图导出项目war包
- 将war包放入tomcat的webapp
- `cd /usr/src/apache-tomcat-8.5.66/bin`
- `./shutdown.sh `
- `./startup.sh `

7. 配置nginx代理

- `cd /usr/local/nginx/conf`
- ` vim nginx.conf`
- 如图更改配置文件
- `cd /usr/local/nginx/sbin`
- `./nginx -s stop`
- ` ./nginx `
- 输入ip地址即可访问









