package com.krushit.common.test;

import com.krushit.common.config.RedisConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RedisConfig.class);

        RedisTemplate<String, Object> redisTemplate = context.getBean(RedisTemplate.class);

        //redisTemplate.opsForValue().set("name", "Krushit");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("Name :: " + name);

        context.close();
    }
}
