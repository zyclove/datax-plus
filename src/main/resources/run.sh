docker run --name datax-plus -d -p 8080:8080 -u root \
    --restart=always \
    -v $(pwd)/logs:/opt/local/logs \
    -v $(pwd)/tempdir:/opt/local/tempdir \
    -e JAVA_OPTS=" -server -Djava.security.egd=file:/dev/./urandom -Ddatax.home=/opt/local/datax  -Dtempfile.dir=/opt/local/tempdir" \
    registry.cn-hangzhou.aliyuncs.com/nox60/datax-plus:0.0.2



# hook

https://blog.csdn.net/qq_39684784/article/details/119180398
https://my.oschina.net/mchange/blog/5072563

