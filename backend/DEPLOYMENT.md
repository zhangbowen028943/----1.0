# 教务管理系统部署指南

## 环境要求
- Java 17+
- MySQL 8.0+
- Maven 3.8+（[安装指南](https://maven.apache.org/install.html)）
- Node.js 18+（用于前端构建）

## 数据库配置
1. 创建数据库用户（使用schema.sql中的配置）：
```sql
CREATE USER 'academic_user'@'%' IDENTIFIED BY 'secure_password';
GRANT ALL PRIVILEGES ON academic_system.* TO 'academic_user'@'%';
FLUSH PRIVILEGES;
```
2. 修改应用配置（application.properties）：
```properties
spring.datasource.url=jdbc:mysql://<数据库地址>:3306/academic_system
spring.datasource.username=academic_user
spring.datasource.password=secure_password
```

## 系统构建
```bash
mvn clean package
```

## 启动服务
```bash
java -jar target/academic-system-0.0.1-SNAPSHOT.jar
```

## 初始化数据库
```bash
mysql -u root -p < schema.sql
```

## 安全建议
1. 生产环境请修改默认数据库密码
2. 定期执行安全扫描：
```bash
mvn dependency-check:check
```

## API文档
访问地址：http://localhost:8080/swagger-ui.html

通过OpenAPI 3.0规范生成的实时接口文档，包含所有RESTful接口说明和测试功能。
2. 定期备份数据库
3. 启用SSL数据库连接