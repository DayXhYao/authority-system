# authority-system
一个领域驱动设计的权限系统

该项目是以领域驱动设计为理念开源的一个权限系统，该系统是我在学习领域驱动后的一个尝试落地的项目。里面对于领域的理解和包的拆分都是我根据自己经验进行融合的。希望大家可以一起探讨领域驱动落地的可行方案。



## 技术栈

该项目使用的技术栈为均为国内通用框架，文档较为齐全，便于开发人员二次开发

- 开发语言：Java
- 项目管理：Maven
- 数据库：Mysql、Redis
- 核心框架：Spring家族、Mybatis、Lombok、Hutool等



## 项目结构

authority-system // maven 父pom

---- authority-system-common  // 公共代码，常量或者工具栏

---- authority-system-core  // 全局配置、或者全局拦截器等等

---- authority-system-api  // 各个服务的开放接口

---- authority-system-server  // 业务代码，后续可拆分为微服务





## 致敬

参考书籍：《领域驱动设计》 -Eric Evans
