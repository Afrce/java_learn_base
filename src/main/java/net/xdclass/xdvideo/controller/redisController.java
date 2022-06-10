package net.xdclass.xdvideo.controller;

import net.xdclass.xdvideo.model.bo.UserBo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class redisController {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redis/{key}/{value}")
    public Object setString(@PathVariable String key,
                          @PathVariable String value) {
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set(key, value);
        System.out.println(key + ":" + value);
        return "redis 设置数据";
    }

    @GetMapping("/redis/{key}")
    public Object setString(@PathVariable String key) {
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        Object value = valueOperations.get(key);
        return "redis 获取数据：" + "key:" + key + ";value:" + value;
    }

    @GetMapping("/redis/hash")
    public Object setHash() {
        UserBo user = new UserBo();
        user.setId(1L);
        user.setEmail("test");
        user.setUsername("test");
        stringRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer(UserBo.class));

        stringRedisTemplate.opsForHash().put("hash:hash:hash1", "key1", user);
        stringRedisTemplate.opsForHash().put("hash:hash:hash2", "key1", user);
        stringRedisTemplate.opsForHash().put("hash:hash:hash3", "key1", user);
        return "redis 设置hash数据";
    }

    @GetMapping("/redis/get/hash")
    public Object getHash() {
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(UserBo.class));
        UserBo user = (UserBo) stringRedisTemplate.opsForHash().get("hash:hash:hash1", "key1");
        return "redis 获取hash数据：" + user.toString();
    }
}
