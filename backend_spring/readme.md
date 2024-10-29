## 运行这个 Spring Boot 后端：

确保你已安装 Java 11 或更高版本和 Maven
在 backend-spring 目录下运行：

mvn spring-boot:run

## 启动应用程序后，你可以通过以下 URL 访问 Swagger UI：

Swagger UI: http://localhost:3000/swagger-ui/
API 文档 JSON: http://localhost:3000/v2/api-docs

## 安装并运行 MongoDB：
如果你还没有安装 MongoDB，你需要先安装它。安装步骤可能因操作系统而异：

启动：
sudo systemctl start mongod
sudo systemctl restart mongod

检查 MongoDB 服务的状态：
systemctl status mongod

sudo systemctl daemon-reload

停止：
sudo systemctl stop mongod

配置文件：（/etc/mongod.conf）