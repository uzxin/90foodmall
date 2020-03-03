package com.cuit.foodmall.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
/**
 * @author: YX
 * @date: 2020/3/3 11:25
 * @description: redis配置
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisConfig {

	@Autowired
	RedisSerializer<Object> valueSerializer;
	@Autowired
	StringRedisSerializer keySerializer;

	@Bean
	StringRedisSerializer stringRedisSerializer() {
		return new StringRedisSerializer();
	}

	@Bean
	RedisSerializer<Object> redisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	/**
	 * 设置 redis 数据默认过期时间
	 * 设置@cacheable 序列化方式
	 */
	@Bean
	public RedisCacheManager redisCacheConfiguration(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration config = RedisCacheConfiguration
				.defaultCacheConfig()
				.serializeKeysWith(
						RedisSerializationContext
								.SerializationPair
								.fromSerializer(keySerializer))
				.serializeValuesWith(
						RedisSerializationContext
								.SerializationPair
								.fromSerializer(valueSerializer)
				)
				.entryTtl(Duration.ofDays(30))
				.disableCachingNullValues();

		RedisCacheManager redisCacheManager = RedisCacheManager.builder(new MyRedisCacheWriter(connectionFactory))
				.cacheDefaults(config)
				.transactionAware()
				.build();

		log.debug("自定义RedisCacheManager加载完成");
		return redisCacheManager;

	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

		redisTemplate.setConnectionFactory(factory);

		redisTemplate.setKeySerializer(keySerializer);
		redisTemplate.setValueSerializer(valueSerializer);

		redisTemplate.setHashKeySerializer(keySerializer);
		redisTemplate.setHashValueSerializer(valueSerializer);
		log.debug("自定义RedisTemplate加载完成");
		return redisTemplate;
	}
}

