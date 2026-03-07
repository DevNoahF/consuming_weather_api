package com.example.weatherapiconsuming.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.URI;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.url}")
    private String redisUrl;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        URI uri = URI.create(redisUrl);

        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(uri.getHost());
        redisConfig.setPort(uri.getPort());

        // Extract password from URI (format: user:password)
        if (uri.getUserInfo() != null) {
            String[] userInfo = uri.getUserInfo().split(":", 2);
            if (userInfo.length > 1) {
                redisConfig.setPassword(userInfo[1]);
            }
        }

        LettuceClientConfiguration.LettuceClientConfigurationBuilder clientConfigBuilder =
            LettuceClientConfiguration.builder();

        // Enable SSL if URL starts with "rediss://"
        if (redisUrl.startsWith("rediss://")) {
            clientConfigBuilder.useSsl();
        }

        return new LettuceConnectionFactory(redisConfig, clientConfigBuilder.build());
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
