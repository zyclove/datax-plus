docker run -d \
  --name=filebeat \
  --user=root \
  --link simple-redis:redis \
  -v /opt/local/containers/datax-plus/logs:/opt/local/logs \
  -v $(pwd)/data:/usr/share/filebeat/data \
  --volume="$(pwd)/filebeats.yml:/usr/share/filebeat/filebeat.yml:ro" \
  docker.elastic.co/beats/filebeat:7.15.2 filebeat -e



# https://www.elastic.co/guide/en/beats/filebeat/current/using-environ-vars.html
# docker run -it  -e REDIS_IP=172.16.56.64 -e REDIS_PASSWORD=dev@2019 -e REDIS_LOG_KEY=sadf  newbeat:0.4 /bin/bash