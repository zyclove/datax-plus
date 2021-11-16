docker run -d \
  --name=filebeat \
  --user=root \
  --volume="$(pwd)/filebeats.yml:/usr/share/filebeat/filebeat.yml:ro" \
  docker.elastic.co/beats/filebeat:7.15.2 filebeat