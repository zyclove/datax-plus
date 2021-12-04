FROM docker.elastic.co/beats/filebeat:7.15.2
#维护者信息
MAINTAINER xxx.cechealth.cn

#环境变量设置

#添加文件
ADD filebeat.yml /usr/share/filebeat/filebeat.yml

#容器启动时内部运行的命令
ENTRYPOINT ["sh","-c","filebeat -e"]