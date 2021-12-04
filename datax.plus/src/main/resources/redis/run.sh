docker run --name simple-redis -d \
-p 6379:6379 \
--volume="$(pwd)/redis.conf:/usr/local/etc/redis/redis.conf:ro" \
redis:5.0.0-alpine /usr/local/etc/redis/redis.conf
