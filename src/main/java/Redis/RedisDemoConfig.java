package Redis;


import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisDemoConfig {
 
 @Value(value="${spring.redis.host}")
 private String host;
 
 @Value(value="${spring.redis.port}")
 private String port;
 
  
 
 @Bean
 JedisConnectionFactory jedisConnectionFactory() {
  RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(Integer.valueOf(port));        

        JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(Integer.valueOf(timeout)));// connection timeout

        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,  jedisClientConfiguration.build());
        return jedisConFactory;
 }
 
 @Bean
 public RedisTemplate<String, Object> redisTemplate() {
	 RedisTemplate<String, Object> template = new RedisTemplate<>();
     template.setConnectionFactory(jedisConnectionFactory());
     template.setKeySerializer(new StringRedisSerializer());
     template.setHashKeySerializer(new StringRedisSerializer());
     template.setHashKeySerializer(new JdkSerializationRedisSerializer());
     template.setValueSerializer(new JdkSerializationRedisSerializer());
     template.setEnableTransactionSupport(true);
     template.afterPropertiesSet();
     return template;
 }
}