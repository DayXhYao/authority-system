package com.day.authority.core.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

/**
 * @author Day Xh Yao io
 * @version 1.0
 * @descript Redis 缓存配置类 采用Fastjson序列方式
 * @descript GenericJackson2JsonRedisSerializer 可能会出现对JDK新增 java.time.*包下日期类型不支持的情况
 * @date 2020-11-05 10:03
 * @see FastJsonRedisSerializer
 */
@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {


    /**
     * redisson协议前缀
     */
    private static final String SCHEMA_PREFIX = "redis://";

    /**
     * 锁超时时间
     */
    @Value("${spring.redis.lockTimeOut:30000}")
    private long lockWatchTimeOut;


    /**ControllerLogAop
     * 例如：RedisCacheConfig
     * 返回 CachingConfigurerSupport.RedisCacheConfig#param1.param2.null
     *
     * @return 自定义键生成器
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (cls, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getClass().getSimpleName());
            sb.append(".");
            sb.append(sb.getClass().getName());
            if (params.length > 0) {
                sb.append("#");
            }
            String sp = "";
            for (Object object : params) {
                sb.append(sp);
                if (object == null) {
                    sb.append("NULL");
                } else {
                    sb.append(object.toString());
                }
                sp = ".";
            }
            return sb.toString();

        };
    }



    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // set key serializer
        StringRedisSerializer serializer = new StringRedisSerializer();
        // 设置key序列化类，否则key前面会多了一些乱码
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);

        // fastjson serializer
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);
        // 如果 KeySerializer 或者 ValueSerializer 没有配置，则对应的 KeySerializer、ValueSerializer 才使用这个 Serializer
        template.setDefaultSerializer(fastJsonRedisSerializer);

        // factory
        template.setConnectionFactory(redisConnectionFactory);
        //非spring注入使用RedisTemplate,需先调用afterPropertiesSet()方法
        //否则会出call afterPropertiesSet() before using it
        template.afterPropertiesSet();
        return template;
    }



    @Bean
    public RedissonClient redissonClient(RedisProperties redisProperties) {
        Config config = new Config();
        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
        RedisProperties.Cluster redisPropertiesCluster = redisProperties.getCluster();
        if (redisPropertiesCluster != null) {
            //集群redis
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            for (String cluster : redisPropertiesCluster.getNodes()) {
                clusterServersConfig.addNodeAddress(SCHEMA_PREFIX + cluster);
            }
            if (StringUtils.hasText(redisProperties.getPassword())) {
                clusterServersConfig.setPassword(redisProperties.getPassword());
            }
            clusterServersConfig.setTimeout((int) redisProperties.getTimeout().toMillis());
            clusterServersConfig.setPingConnectionInterval(30000);
        } else if (StringUtils.hasText(redisProperties.getHost())) {
            //单点redis
            SingleServerConfig singleServerConfig = config.useSingleServer().
                    setAddress(SCHEMA_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort());
            if (StringUtils.hasText(redisProperties.getPassword())) {
                singleServerConfig.setPassword(redisProperties.getPassword());
            }
            singleServerConfig.setTimeout((int) redisProperties.getTimeout().toMillis());
            singleServerConfig.setPingConnectionInterval(30000);
            singleServerConfig.setDatabase(redisProperties.getDatabase());
        } else if (sentinel != null) {
            //哨兵模式
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            sentinelServersConfig.setMasterName(sentinel.getMaster());
            for (String node : sentinel.getNodes()) {
                sentinelServersConfig.addSentinelAddress(SCHEMA_PREFIX + node);
            }
            if (StringUtils.hasText(redisProperties.getPassword())) {
                sentinelServersConfig.setPassword(redisProperties.getPassword());
            }
            sentinelServersConfig.setTimeout((int) redisProperties.getTimeout().toMillis());
            sentinelServersConfig.setPingConnectionInterval(30000);
            sentinelServersConfig.setDatabase(redisProperties.getDatabase());
        }
        config.setLockWatchdogTimeout(lockWatchTimeOut);
        return Redisson.create(config);
    }
}