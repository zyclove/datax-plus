docker run --name datax-plus -d -p 8080:8080 -u root \
    --restart=always \
    -v /opt/local/containers/datax-plus/logs:/opt/local/logs \
    -v /opt/local/containers/datax-plus/tempdir:/opt/local/tempdir \
    -e JAVA_OPTS=" -server -Djava.security.egd=file:/dev/./urandom -Ddatax.home=/opt/local/datax  -Dtempfile.dir=/opt/local/tempdir" \
    registry.cn-hangzhou.aliyuncs.com/nox60/datax-plus:0.0.2
