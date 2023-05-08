# 使用的基础镜像
FROM openjdk:11-jdk-slim

# 镜像作者信息
LABEL maintainer="1196829219@qq.com"

# 将本地文件夹挂载到当前容器中
VOLUME /tmp

# 复制jar包到容器中
COPY build/libs/activities-0.0.1-SNAPSHOT.jar app.jar

# 运行jar包
ENTRYPOINT ["java","-jar","/app.jar"]
