# datax_java_without_python

This project could run dataX with out python env.

docker run --name dataxx-plus -d -p 8080:8080 -u root \
    --restart=always \
    -v /opt/local/containers/datax-plus/logs:/opt/ \
    -v /usr/share/zoneinfo/Asia/Shanghai:/etc/localtime  \
    jenkins/jenkins:lts
