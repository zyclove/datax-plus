docker run --name datax-plus -d -p 8080:8080 -u root \
    --restart=always \
    -v $(pwd)/logs:/opt/local/logs \
    -v $(pwd)/tempdir:/opt/local/tempdir \
    -e JAVA_OPTS=" -server -Djava.security.egd=file:/dev/./urandom -Ddatax.home=/opt/local/datax  -Dtempfile.dir=/opt/local/tempdir" \
    registry.cn-hangzhou.aliyuncs.com/nox60/datax-plus:0.0.2



docker run --name datax-plus -d -p 8888:8080 -u root \
    --restart=always \
    --link simple-redis:redis \
    -v /opt/local/containers/datax-plus/logs:/opt/local/logs \
    -v /opt/local/containers/datax-plus/hook:/opt/local/datax/hook \
    -v /opt/local/containers/datax-plus/tempdir:/opt/local/tempdir \
    -e JAVA_OPTS=" -server -Djava.security.egd=file:/dev/./urandom -Ddatax.home=/opt/local/datax  -Dtempfile.dir=/opt/local/tempdir  " \
    -e MYSQL_HOST="1212" \
    -e MYSQL_PORT="3306" \
    -e MYSQL_USERNAME="111" \
    -e MYSQL_PASSWORD="2222" \
    registry.cn-hangzhou.aliyuncs.com/nox60/datax-plus:0.0.26




# hook

# https://blog.csdn.net/qq_39684784/article/details/119180398
# https://my.oschina.net/mchange/blog/5072563

mvn archetype:generate -DgroupId=com.dataxplus.hook.utils -DartifactId=dataplus-hook  -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

# https://www.jdoodle.com/execute-sql-online/