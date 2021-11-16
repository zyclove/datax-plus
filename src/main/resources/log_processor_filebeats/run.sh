docker run -d \
  --name=filebeat \
  --user=root \
  --link simple-redis:redis \
  -v /opt/local/containers/datax-plus/logs:/opt/local/logs \
  -v $(pwd)/data:/usr/share/filebeat/data \
  --volume="$(pwd)/filebeats.yml:/usr/share/filebeat/filebeat.yml:ro" \
  docker.elastic.co/beats/filebeat:7.15.2 filebeat -e
