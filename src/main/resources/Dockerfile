FROM registry.cn-hangzhou.aliyuncs.com/nox60/datax-base:0.0.1

MAINTAINER nox60@hotmail.com

#环境变量设置
ENV LANG="zh_CN.UTF-8"
# ENV JAVA_OPTS=" -server -Xms2048m -Xmx2048m -Xmn256m -Xss1m -Djava.security.egd=file:/dev/./urandom "
ENV JAVA_OPTS=" -server -Djava.security.egd=file:/dev/./urandom "
ENV TZ=Asia/Shanghai LANG="C.UTF-8"
ENV JWT_AUTH_OPEN=true

#添加jar包
ADD ./*.jar /opt/

#容器启动时内部运行的命令
ENTRYPOINT ["sh","-c","java $JAVA_OPTS  -Dfile.encoding=utf-8 -jar /opt/*.jar"]